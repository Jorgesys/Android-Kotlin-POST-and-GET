# Android-Kotlin-POST-and-GET
Android POST and GET requests using Kotlin 


# What does it look like?
![Jorgesys POST and GET requests in Kotlin](https://i.stack.imgur.com/T4yp6.png)


Example of POST request in Java:

```
    public class makePOST extends AsyncTask<String, String, String> {

        public makePOST() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0]; // Url POST
            String data = params[1]; //Datos enviados POST
            String response = "";
            OutputStream out = null;

            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                out = new BufferedOutputStream(urlConnection.getOutputStream());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                out.close();
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("Response : " + s);
        }
    }
```    
    
    Example of how to call the AsyncTask to make a POST request:
    
    
```
      new makePOST().execute("https://postman-echo.com/post", "Jorgesys was here! :-) !");
```      
      
    
    
