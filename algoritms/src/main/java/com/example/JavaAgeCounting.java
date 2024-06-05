package com.example;

import java.util.*;
import java.io.*;
import java.net.*;

/****
 *
 *
 * https://coderbyte.com/candidate-assessment#
 * Java Age Counting
 *
 */
class TechTry {
    class Person {
        private String key;
        private int age;

        public Person(String key, int age) {
            this.key = key;
            this.age = age;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person [key=" + key + ", age=" + age + "]";
        }
    }

    public void fetchData() {
        System.setProperty("http.agent", "Chrome");
        try {
            URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            Scanner sc = new Scanner(inputStream);
            StringBuilder sb = new StringBuilder();

            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }

            String data = sb.toString();
            String[] items = data.replace("{\"data\":\"", "").replace("\"}", "").split(", ");
            List<Person> persons = new ArrayList<>();
            List<Person> personsGreaterthan50 = new ArrayList<>();
            int count = 0;

            for (int i = 0; i <items.length ; i++)
            {

                String[] keyVal = items[i].split("=");
                if (keyVal[0].equals("age")) {
                    String[] keyValkey = items[i-1].split("=");
                    String key = keyValkey[1];
                    int age = Integer.parseInt(keyVal[1]);
                    Person person=new Person(key, age);
                    persons.add(person);
                    if (age > 50) {
                        personsGreaterthan50.add(person);
                        count++;
                    }
                }
            }

            // Print all items
            //for (Person person : personsGreaterthan50) {
            //  System.out.println(person);
            //}

            //System.out.println("Count of persons with age >= 50: " + count+" / "+ persons.size());
            System.out.println(count);

        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    public static void main(String[] args) {
        TechTry techTry = new TechTry();
        techTry.fetchData();
    }
}
