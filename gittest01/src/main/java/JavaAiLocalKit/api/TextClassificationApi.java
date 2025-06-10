package JavaAiLocalKit.api;


import git.JavaAiLocalKit.core.TextClassificationCore;

import java.util.Map;

public class TextClassificationApi {

    /**
     * Perform text classification using the provided tokenizer and model file paths.
     *
     * @param tokenizerFilePath Path to the tokenizer file (absolute path or classpath-relative path)
     * @param modelFilePath Path to the ONNX model file (absolute path or classpath-relative path)
     * @param text The input text to classify
     * @return A result map containing the predicted label and probability distribution
     * @throws Exception If an error occurs during prediction
     */
    public static Map<String, Object> classifyText(String tokenizerFilePath, String modelFilePath, String text) throws Exception {
        try (TextClassificationCore core = new TextClassificationCore(tokenizerFilePath, modelFilePath)) {
            return core.a(text);
        }
    }

}
