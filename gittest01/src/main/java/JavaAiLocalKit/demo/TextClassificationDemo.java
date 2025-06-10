package JavaAiLocalKit.demo;


import JavaAiLocalKit.api.TextClassificationApi;


import java.util.Map;

// TODO Simple usage demo for the utility class
public class TextClassificationDemo {

    public static void main(String[] args) throws Exception {
        // Specify the ONNX model name and tokenizer file name from resource path
        // These files should be placed in the resources directory of the project
        String tokenizerPath = "/TextClassificationDemoModelEnglish/tokenizer.json";
        String modelPath = "/TextClassificationDemoModelEnglish/model.onnx";

        // Input text for sentiment analysis
        // This is the text we want to analyze for emotional sentiment
        String testText = "The room was terrible, not comfortable at all.";

        // Call the TextClassificationApi with parameters matching the task type
        // classifyText() method takes the tokenizer path, model path, and input text
        // It returns a Map containing classification results
        Map<String, Object> result = TextClassificationApi.classifyText(tokenizerPath, modelPath, testText);

        // Get and print the prediction result
        // The "label" key contains the predicted sentiment class (e.g., "negative" or "positive")
        System.out.println("Prediction result: " + result.get("label"));

        // Extract and print the probability distribution
        // The "probabilities" key contains a float array with confidence scores for each class
        float[] probs = (float[]) result.get("probabilities");

        // Format and print the probabilities with 4 decimal places precision
        // Assuming index 0 is negative sentiment and index 1 is positive sentiment
        System.out.printf("Probability distribution: negative=%.4f, positive=%.4f%n", probs[0], probs[1]);
    }
}

// TODO 中文demo

//    public static void main(String[] args) throws Exception {
//        // Specify the ONNX model name and tokenizer file name from resource path
//        String tokenizerPath = "/TextClassificationDemoModelChinese/tokenizer.json";
//        String modelPath = "/TextClassificationDemoModelChinese/bert_sentiment.onnx";
//
//        String testText = "房间一点都不舒服";
//        Map<String, Object> result = TextClassificationApi.classifyText(tokenizerPath, modelPath, testText);
//
//        System.out.println("Prediction result: " + result.get("label"));
//        float[] probs = (float[]) result.get("probabilities");
//        System.out.printf("Probability distribution: negative=%.4f, positive=%.4f%n", probs[0], probs[1]);
//    }