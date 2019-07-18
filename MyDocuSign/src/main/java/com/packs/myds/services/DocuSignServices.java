package com.packs.myds.services;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.sun.jersey.core.util.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.docusign.esign.model.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.packs.myds.constants.DocuSignConstants.*;

@Service
public class DocuSignServices {

    String accessToken;

//    String accessToken = ACCESS_TOKEN;
    String accountId = ACCOUNT_ID;
    String signerName = SIGNER_NAME;
    String signerEmail = SIGNER_EMAIL;
    String basePath = API_BASEPATH;
    String baseUrl = BASE_URL;
    String clientUserId = "123";
    String authenticationMethod = "None";

    public String signFile(MultipartFile file) throws Exception {

        Tabs signerTabs = new Tabs();
        EnvelopesApi envelopesApi = new EnvelopesApi();

        byte[] buffer =file.getBytes();
        String docBase64 = new String(Base64.encode(buffer));

        Document document=createDocObject(docBase64);

        Signer signer = getSignerObj();

        SignHere signHere=getSignHereTab();

        // Add the tabs to the signer object
        signerTabs.setSignHereTabs(Arrays.asList(signHere));
        signer.setTabs(signerTabs);
        EnvelopeDefinition envelopeDefinition=getEnvelopeDefinition(document,signer);

        // Call DocuSign to create and send the envelope and get enveloperId;
        EnvelopeSummary envelopeSummary=getEnvelope(envelopeDefinition);
         String envelopeId = envelopeSummary.getEnvelopeId();
         String envelopeUrl=envelopeSummary.getUri();

        // Request a Recipient View URL (the Signing Ceremony URL)
        RecipientViewRequest viewRequest=getViewRequeset();

        // call the CreateRecipientView API

        ViewUrl results = envelopesApi.createRecipientView(accountId, envelopeId, viewRequest);

        // return Recipient View URL
        return results.getUrl();
//        return envelopeUrl;
    }

    // Create the DocuSign document object
    public Document createDocObject(String docBase64){
        Document document=new Document();
        document.setDocumentBase64(docBase64);
        document.setName("Example document");
        document.setFileExtension("pdf");
        document.setDocumentId("1");
        return document;
    }

    // Create signer object
    public Signer getSignerObj(){
        Signer signer = new Signer();
        signer.setEmail(signerEmail);
        signer.setName(signerName);
        signer.clientUserId(clientUserId);
        signer.recipientId("1");
        return signer;
    }

    public SignHere getSignHereTab(){
        SignHere signHere = new SignHere();
        signHere.setDocumentId("1");
        signHere.setPageNumber("1");
        signHere.setRecipientId("1");
        signHere.setTabLabel("SignHereTab");
        signHere.setXPosition("195");
        signHere.setYPosition("147");
        return signHere;
    }

    public EnvelopeDefinition getEnvelopeDefinition(Document document,Signer signer){
        Recipients recipients = new Recipients();

        EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
        envelopeDefinition.setEmailSubject("Please sign this document");
        envelopeDefinition.setDocuments(Arrays.asList(document));
        // Add the recipient to the envelope object
        recipients.setSigners(Arrays.asList(signer));
        envelopeDefinition.setRecipients(recipients);
        envelopeDefinition.setStatus("sent");
        return envelopeDefinition;
    }

    public EnvelopeSummary getEnvelope(EnvelopeDefinition envelopeDefinition) throws Exception{
        ApiClient apiClient = new ApiClient(basePath);
        String accessToken=getAccessToken();
        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
        return envelopesApi.createEnvelope(accountId, envelopeDefinition);
    }

    public RecipientViewRequest getViewRequeset(){
        // Set the url where you want the recipient to go once they are done signing, typically a callback
        RecipientViewRequest viewRequest = new RecipientViewRequest();
        viewRequest.setReturnUrl(baseUrl + "/returnpage");
        viewRequest.setAuthenticationMethod(authenticationMethod);
        viewRequest.setEmail(signerEmail);
        viewRequest.setUserName(signerName);
        viewRequest.setClientUserId(clientUserId);
        return viewRequest;
    }

    public String getAccessToken() throws Exception{

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(ACCESS_TOKEN_URI));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + ACCESS_TOKEN_URI);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//    HttpResponse<String> responses = Unirest
//            .get(ACCESS_TOKEN_TEST)
//            .header("accept", "application/json")
//            .queryString("response_type","code")
//            .queryString("scope","signature")
//            .queryString("client_id","09098b8d-4ec3-4d80-99d1-097806341c60")
//            .queryString("redirect_uri","http://localhost:8080/api/ds/getaccesstoken")
//            .asString();

//    String body=responses.;
        return "token123";
    }

    public String parseToken(String code) throws Exception{

        HttpResponse<JsonNode> responses = Unirest
                .post("https://account-d.docusign.com/oauth/token")
                .header("Authorization","Basic MDkwOThiOGQtNGVjMy00ZDgwLTk5ZDEtMDk3ODA2MzQxYzYwOmI3MzE4M2Y2LTQzZmUtNDE2YS1iZTEyLWFhZjJlZGE3NTM4Mw==")
                .header("Content-Type","application/x-www-form-urlencoded")
                .field("code",code)
                .field("grant_type","authorization_code").asJson();
        JSONObject jsonObject=responses.getBody().getObject();
//        Map<String,String> responseMap=new HashMap((Map) jsonObject.getJSONArray("map"));
        String token=jsonObject.getString("access_token");
        accessToken =token;
        return accessToken;
    }


}