package io.github.nyghtfull.util.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenUtil implements IAccess {
    public static String[] getProfileInfo(String token) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.minecraftservices.com/minecraft/profile");
        request.setHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse response = client.execute(request);
        String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        String IGN = jsonObject.get("name").getAsString();
        String UUID = jsonObject.get("id").getAsString();
        return new String[]{IGN, UUID};
    }

    private static Map<String, String> uuidCache = new HashMap<>();

    public static String[] getProfileInfoT(String token) throws IOException {
        if (uuidCache.containsKey(token)) {
            String uuid = uuidCache.get(token);
            return new String[]{"", uuid};
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.minecraftservices.com/minecraft/profile");
        request.setHeader("Authorization", "Bearer " + token);

        try (CloseableHttpResponse response = client.execute(request)) {
            String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

            String IGN = jsonObject.has("name") ? jsonObject.get("name").getAsString() : "Unknown";
            String UUID = jsonObject.has("id") ? jsonObject.get("id").getAsString() : "Unknown";
            uuidCache.put(token, UUID);
            return new String[]{IGN, UUID};
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Failed To Parse Json", e);
        } finally {
            client.close();
        }
    }

    public static Boolean validateSession(String token) throws IOException {
        try {
            String[] profileInfo = getProfileInfo(token);
            String IGN = profileInfo[0];
            String UUID = profileInfo[1];
            return IGN.equals(Minecraft.getMinecraft().getSession().getUsername()) && UUID.equals(Minecraft.getMinecraft().getSession().getPlayerID());
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean checkOnline(String UUID)
    {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet requests = new HttpGet("https://api.slothpixel.me/api/players/" + UUID);
            CloseableHttpResponse response = client.execute(requests);
            String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
            return jsonObject.get("online").getAsBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int changeName(String newName,String token) throws IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut request = new HttpPut("https://api.minecraftservices.com/minecraft/profile/name/" + newName);
        request.setHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode();
    }

    public static int changeSkin(String url,String token) throws IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://api.minecraftservices.com/minecraft/profile/skins");
        request.setHeader("Authorization", "Bearer " + token);
        request.setHeader("Content-Type", "application/json");
        String jsonString = String.format("{ \"variant\": \"classic\", \"url\": \"%s\"}", url);
        request.setEntity(new StringEntity(jsonString));
        CloseableHttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode();
    }

    private static final Map<String, ResourceLocation> SKIN_CACHE = new HashMap<>();

    public static ResourceLocation getResourceLocation(String uuid) {
        if (SKIN_CACHE.containsKey(uuid)) return SKIN_CACHE.get(uuid);

        String imageUrl = "http://crafatar.com/avatars/" + uuid;
        ResourceLocation resourceLocation = new ResourceLocation("skins/" + uuid + "?overlay=true");
        ThreadDownloadImageData headTexture = new ThreadDownloadImageData(null, imageUrl, null, null);
        mc.getTextureManager().loadTexture(resourceLocation, headTexture);
        SKIN_CACHE.put(uuid, resourceLocation);
        AbstractClientPlayer.getDownloadImageSkin(resourceLocation, uuid);
        return resourceLocation;
    }

    public static String uuidOf(String name) {
        String data = scrape("https://api.mojang.com/users/profiles/minecraft/" + name);
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        if (jsonObject == null || !jsonObject.has("id")) return null;
        return jsonObject.get("id").getAsString();
    }

    private static String scrape(String url) {
        String content = "";
        try {
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", "Chrome Version 88.0.4324.150");
            connection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line + System.lineSeparator();
            }
            bufferedReader.close();
        } catch (IOException ignored) {
        }
        return content;
    }
}
