package utilities;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONReader {

    //****************************************** don't touch this section *************************************************
    InputStream dataInputStream;
    JSONObject jsonObject;
    JSONObject jsonObjectFile;


    public JSONObject readData(String dataFilePath) throws IOException {
        try{
            dataInputStream = getClass().getClassLoader().getResourceAsStream(dataFilePath);
            JSONTokener tokener = new JSONTokener(dataInputStream);
            jsonObject = new JSONObject(tokener);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(dataInputStream != null){
                dataInputStream.close();
            }
        }
        return jsonObject;
    }

    public String getStringJsonObject(String dataFilePath, String jsonObjectKey, String jsonObjectValue) throws IOException {
        jsonObjectFile = readData(dataFilePath);
        return jsonObjectFile.getJSONObject(jsonObjectKey).getString(jsonObjectValue);
    }

    public String getStringJsonObject(String dataFilePath, String jsonObjectKey1, String jsonObjectKey2, String jsonObjectValue) throws IOException {
        jsonObjectFile = readData(dataFilePath);
        return jsonObjectFile.getJSONObject(jsonObjectKey1).getJSONObject(jsonObjectKey2).getString(jsonObjectValue);
    }
//****************************************** don't touch this section *************************************************

}

