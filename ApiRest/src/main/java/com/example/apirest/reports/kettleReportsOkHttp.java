package com.example.apirest.reports;


import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class kettleReportsOkHttp {

    private static Logger logger = Logger.getLogger(kettleReportsOkHttp.class.getName());

    private void donwloadExcel() throws InterruptedException {
        String reservedId = "";
        int statushttp=0;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://10.1.0.160:8080/pentaho/plugin/reporting/api/jobs/reserveId")
                .method("POST", body)
                .addHeader("Authorization", "Basic YWRtaW46Z20yMDEycGVudGFobzE=")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBodyString = response.body().string();
            logger.info(response.toString());
            logger.info(responseBodyString);
            JSONObject obj = new JSONObject(responseBodyString);
            reservedId = (String) obj.get("reservedId");
            statushttp=response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--------------------------------------------------------------------------------------------------------------
        if (statushttp != 200 || !Optional.ofNullable(reservedId).isPresent() || reservedId.length()<1)
        {
            return;
        }
        mediaType = MediaType.parse("application/x-www-form-urlencoded");
        body = RequestBody.create(mediaType, "ts=1647531888434" +
                "&fecha_inicio=2019-08-01" +
                "&fecha_fin=2021-03-30" +
                "&output-target=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;page-mode=flow&accepted-page=0" +
                "&showParameters=true&renderMode=REPORT&htmlProportionalWidth=false&query-limit-ui-enabled=true" +
                "&query-limit=0&maximum-query-limit=0&reservedId=" + reservedId);
        Request request2 = new Request.Builder()
                .url("http://10.1.0.160:8080/pentaho/api/repos/:public:reports:HistoricoVentasV1.prpt/reportjob")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic YWRtaW46Z20yMDEycGVudGFobzE=")
                .build();
        try (Response response2 = client.newCall(request2).execute()) {
            String responseBodyString = response2.body().string();
            logger.info(response2.toString());
            logger.info(responseBodyString);
            if (responseBodyString.length()>0) {
                JSONObject obj = new JSONObject(responseBodyString);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        //--------------------------------------------------------------------------------------------------------------
//        String status = "QUEUED";
//        int intentos = 0;
//        while (!status.equalsIgnoreCase("finished") || !status.equalsIgnoreCase("FAILED") || intentos<10) {
//            request = new Request.Builder()
//                    .url("http://10.1.0.160:8080/pentaho/plugin/reporting/api/jobs/" + reservedId + "/status")
//                    .method("GET", null)
//                    .addHeader("Authorization", "Basic YWRtaW46Z20yMDEycGVudGFobzE=")
//                    .addHeader("Cookie", "JSESSIONID=48204292E752C13A5D1C86C069ACA9C7; server-time=1649271238486; session-expiry=1649278438486")
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                String responseBodyString = response.body().string();
//                logger.info(response.toString());
//                logger.info(responseBodyString);
//                JSONObject obj = new JSONObject(responseBodyString);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            intentos += 1;
//            TimeUnit.SECONDS.sleep(2);
//            logger.info("intentos realizados " + intentos);
//
//        }
    }


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        new kettleReportsOkHttp().donwloadExcel();
    }
}
