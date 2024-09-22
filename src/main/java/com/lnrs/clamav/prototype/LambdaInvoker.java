package com.lnrs.clamav.prototype;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

public class LambdaInvoker {

    public static void main(String[] args) {
        String functionName = "arn:aws:lambda:us-east-1:680378541942:function:clamAvS3UploadFunction:3";
        String filePath = "./test/clam-av-test-file.docx";
        // Encode the file to Base64
        String base64EncodedPayload = FileToBase64Encoder.base64encode(filePath);
        String payload = "{\"body\": \"" + base64EncodedPayload + "\"}";

        // Specify the region
        Region region = Region.US_EAST_1; // Change to your desired region
        LambdaClient lambdaClient = LambdaClient.builder()
                .region(region)
                .build();

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);

        String responsePayload = invokeResponse.payload().asUtf8String();
        System.out.println("Response: " + responsePayload);
    }
}