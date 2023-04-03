package com.example.apirest.reports;

import com.example.ClientEnum;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;


public class kettleReports {
    ClientEnum clientInstance;
    private static Logger logger = Logger.getLogger(kettleReports.class.getName());

    private void donwloadExcel() throws IOException, InterruptedException {
        Map<String, String> params= new HashMap<>();

        params.put("fechaInicio","2021-12-01");
        params.put("fechaFin","2022-01-01");

        final int BUFFER_SIZE = 4096;
        Properties prop = new Properties();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection con = null;

        String resourceName = "application.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tipo = "";
        tipo = params.get("tipo");
        String fechaInicio = params.get("fechaInicio");
        String fechaFin = params.get("fechaFin");

        //String strURL="tipo="+tipo+"&amp;fechaInicio="+fechaInicio+"&amp;fechaFin="+fechaFin+"&amp;report=template/Template2.kjb";
        String strURL = "http://10.1.0.160:8080/pentaho";
        URL url= new URL(strURL);
        clientInstance= ClientEnum.INSTANCE;
        WebTarget target = clientInstance.getClientweb().target(url+"/plugin/reporting/api/jobs/reserveId");
        String userCredentials = prop.getProperty("report_pentaho_server_user")+":"+prop.getProperty("report_pentaho_server_pwd");
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));


        Response response = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_FORM_URLENCODED)

                .header("Authorization",basicAuth)
                .post((Entity.json(null)));

        String works = response.readEntity(String.class);
        logger.info(works);
        response.close();
        JSONObject obj = new JSONObject(works);
        String reservedId=(String) obj.get("reservedId");

        Form data = new Form();
        data.param("ts","1647531888434");
        data.param("fecha_inicio","2021-10-01");
        data.param("fecha_fin","2022-04-01");
        data.param("output-target","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;page-mode=flow");
        data.param("accepted-page","0");
        data.param("showParameters","true");
        data.param("renderMode","REPORT");
        data.param("htmlProportionalWidth","false");
        data.param("query-limit-ui-enabled","true");
        data.param("query-limit","0");
        data.param("maximum-query-limit","0");
        data.param("reservedId",reservedId);

//        // se crea mapa se deberia crear DTO si se requiere esta estrcutura en mas sitios
        Entity<Form> entity = Entity.form(data);
        WebTarget target2 = clientInstance.getClientweb().target(url+"/api/repos/:public:reports:HistoricoVentasV1.prpt/reportjob");

        response = target2.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization",basicAuth)
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type",MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(entity);
        String value = response.readEntity(String.class);
        logger.info(value);
        response.close();



        String status = "QUEUED";
        int intentos = 0;
        while (!status.equalsIgnoreCase("finished") && !status.equalsIgnoreCase("FAILED") && intentos<10)
        {
            target = clientInstance.getClientweb().target(url+"/plugin/reporting/api/jobs/"+reservedId+"/status");
            response = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization",basicAuth)
                    .header("Content-Type",MediaType.APPLICATION_JSON)
                    .get();
            value = response.readEntity(String.class);
            obj = new JSONObject(value);
            status=(String) obj.get("status");
            logger.info(value);
            response.close();
            TimeUnit.SECONDS.sleep(2);
        }

        target = clientInstance.getClientweb().target(url+"/plugin/reporting/api/jobs/"+reservedId+"/content");
        response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization",basicAuth)
                .header("Content-Type",MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .get();

        InputStream is = response.readEntity(InputStream.class);
        File downloadfile = new File("D://Downloads/testnew.xlsx");
        byte[] byteArray = IOUtils.toByteArray(is);
        FileOutputStream fos = new FileOutputStream(downloadfile);
        fos.write(byteArray);
        fos.flush();
        fos.close();
        logger.info(response.toString());
        logger.info( response.readEntity(String.class));





    }



    public static void main(String[] args) throws MalformedURLException {
        try {
            new kettleReports().donwloadExcel();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
