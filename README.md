# Easy Local AI Model Loading Java Library

[](https://www.google.com/search?q=https://maven-badges.herokuapp.com/maven-central/com.github.yourusername/java-ai-local-kit) [](https://www.google.com/search?q=https://jitpack.io/%23yourGitHubUsername/your-repo-name) This library aims to provide Java developers with an **easy-to-use, high-performance** solution for local AI model loading and inference. It encapsulates the ONNX Runtime Java API and Hugging Face Tokenizers, allowing you to seamlessly integrate various AI models into your Java applications without complex configurations or deep underlying details.

-----

## Key Features

* **Simplified API:** Focus on the AI task itself, abstracting away the complexities of ONNX Runtime for a lower development barrier.
* **Local Inference:** All computations are performed on the local device, requiring no internet connection. This ensures data privacy and significantly reduces inference latency.
* **High Performance:** GPU acceleration is theoretically possible (currently not specifically provided due to varying CUDA versions, but a more convenient, unified solution will be offered in the future).
* **Cross-Platform Compatibility:** Supports major operating systems including Windows, Linux, and macOS (leveraging ONNX Runtime).
* **Easy Integration:** Seamlessly integrates with Spring Boot and various other Java projects (a Spring Boot Starter will be provided in the future to further simplify integration).
* **Clear Documentation & Examples:** Provides comprehensive documentation and out-of-the-box example code to help you get started quickly and experience the power of AI firsthand.

-----

## Supported Task Types (with Demo Code)

* **Text Classification:** Suitable for binary or multi-class tasks like sentiment analysis and spam detection.
    * [TextClassificationDemo.java](https://www.google.com/search?q=link/to/TextClassificationDemo.java) (Sentiment Classification Example)
* **Text Generation:** Supports multi-turn conversations, content creation, and more.
    * [TextGenerationDemo.java](https://www.google.com/search?q=link/to/TextGenerationDemo.java) (DeepSeek Conversation Example)

### **Important Note\!**

This project is dedicated to providing **highly encapsulated** AI capabilities. Java developers **don't need to concern themselves with any low-level details**; simply include the dependency to integrate local AI inference into your existing Java projects.

The **only aspect you need to focus on** is the **specific task type supported by the AI model** you are using. This design aims to mirror the concept of `Transforms` in Python, facilitating collaboration between Java developers and AI model developers to ensure model-API compatibility.

Consequently, there are some limitations on the basic input/output formats of ONNX models. The current demos only support direct use with **conventionally structured ONNX models**. In the future, an **ONNX model input/output validation utility** will be added to further reduce the details Java developers need to worry about.

#### **ONNX Model Specifications for Currently Supported Tasks**

##### **Text Classification (e.g., Binary Classification)**

* **Inputs:**
    * `input_ids` (Required)
    * `attention_mask` (Required)
    * `token_type_ids` (Optional, can be omitted)
* **Output:**
    * `logits` (Required)

##### **Text Generation**

* **Inputs:**
    * `input_ids` (Required)
    * `attention_mask` (Required)
    * `position_ids` (Required)
* **Output:**
    * `logits` (Required)

-----

## Planned Task Types (Future Updates)

**🚀 Priority Development Tasks:**

* Image-to-Text
* Zero-Shot Object Detection
* Feature Extraction
* Question Answering

**✨ Long-Term Planned Tasks:**

* Audio Classification
* Text-to-Audio
* Token Classification
* Table Question Answering
* Visual Question Answering
* Document Question Answering
* Fill-Mask
* Summarization
* Translation
* Text2Text Generation
* Zero-Shot Classification
* Zero-Shot Image Classification
* Zero-Shot Audio Classification
* Image Feature Extraction
* Image Segmentation
* Image-Text-to-Text
* Depth Estimation
* Mask Generation
* Image-to-Image

-----

## Quick Start

1.  **Obtain the Core JAR Package:**

    * **If you're running the Demo project:** Ensure you have **manually added the core JAR package** (e.g., `JavaAiLocalKit-Core-ai-1.0.0.jar`, found in the project's `target` directory or on the [Release page](https://www.google.com/search?q=link/to/release/page)) to your IDE's (e.g., IntelliJ IDEA) **External Libraries**.
    

2.  **Add Additional Dependencies (Version Matching):**

    ```xml
    <dependency>
        <groupId>com.microsoft.onnxruntime</groupId>
        <artifactId>onnxruntime</artifactId>
        <version>1.17.0</version>
    </dependency>
    <dependency>
        <groupId>ai.djl.huggingface</groupId>
        <artifactId>tokenizers</artifactId>
        <version>0.33.0</version>
    </dependency>
    <dependency>
        <groupId>org.bytedeco</groupId>
        <artifactId>opencv-platform</artifactId>
        <version>4.10.0-1.5.11</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-math3</artifactId>
        <version>3.6.1</version>
    </dependency>
    ```

3.  **Run Demos:**

    Download and run `TextClassificationDemo.java` or `TextGenerationDemo.java` to experience text classification and text generation.

    #### **Text Sentiment Classification**

    ```java
    public class TextClassificationDemo {

        public static void main(String[] args) throws Exception {
            // Specify the ONNX model and tokenizer file paths (loaded from resources)
            String tokenizerPath = "/TextClassificationDemoModelEnglish/tokenizer.json";
            String modelPath = "/TextClassificationDemoModelEnglish/model.onnx";

            // Text for sentiment analysis
            String testText = "The room was terrible, not comfortable at all.";

            // Call TextClassificationApi for text classification
            Map<String, Object> result = TextClassificationApi.classifyText(tokenizerPath, modelPath, testText);

            // Print prediction result
            System.out.println("Prediction result: " + result.get("label"));

            // Extract and print probability distribution
            float[] probs = (float[]) result.get("probabilities");
            System.out.printf("Probability distribution: negative=%.4f, positive=%.4f%n", probs[0], probs[1]);
        }
    }
    ```

    #### **DeepSeek Conversation**

    [You can set user conversation ID, max history length, max generated tokens, and whether to clear conversation history]

    ```java
    public class TextGenerationDemo {

        public static void main(String[] args) throws IOException, OrtException {
            // Path to the tokenizer configuration file (this example uses a classpath resource)
            String tokenizerPath = "classpath:/TextGenerationDeepSeek/tokenizer.json";

            /*
             * ONNX model file path
             * IMPORTANT:
             * - Since ONNX models often come with separate weight files, it's recommended to use
             * a local absolute path to load the model directly.
             * - If the model does not have separate weight files, a classpath resource path can also be used.
             */
            String modelPath = "D:\\JAVA_code\\common-ai\\src\\main\\resources\\TextGenerationDeepSeek\\model.onnx";

            // Initialize TextGenerationApi service
            TextGenerationApi service = new TextGenerationApi(tokenizerPath, modelPath);

            // Perform a chat interaction:
            // - "user123" is the unique user ID for session management.
            // - The prompt is "Please tell us about the structure of the earth.".
            // - 10 is the maximum number of new tokens to generate per call.
            // - 300 is the maximum token length to retain for history (context window).
            String reply = service.chat("user123", "Please tell us about the structure of the earth.", 10, 300);

            // Print the AI-generated reply
            System.out.println("AI reply: " + reply);

            // Optional: Clear chat history for this user
            // service.clearChatHistory("user123", false);
        }
    }
    ```

-----

## Advanced Configuration (Future Enhancements)

* **GPU Support:** Currently defaults to CPU. In the future, we'll provide a more convenient CUDA version management solution to enable GPU acceleration, allowing Java developers to focus solely on model usage.
* **Distributed Multi-Model Deployment:** Planned for deeper integration with Java microservices architecture to support distributed deployment and management of AI models.

-----

## Business Collaboration & Customization

If you have specific business requirements or scenarios, we're happy to provide end-to-end solutions, from model fine-tuning to Java deployment. Please feel free to contact us for further discussion\!

-----

## License Agreement

This library and the provided demo examples are licensed under the **Apache 2.0 License**. However, **certain core algorithm modules within the main JAR package are under a proprietary license**, prohibiting decompilation and unauthorized redistribution. Please refer to the `LICENSE` file in the project's root directory for detailed terms.

-----

## Dependency Declaration & Acknowledgements

This project relies on the following excellent open-source libraries for its underlying functionalities, and adheres to their respective licenses. We extend our sincere gratitude to them\!

* **ONNX Runtime:** Used for high-performance machine learning model inference. Licensed under the [MIT License](https://github.com/microsoft/onnxruntime/blob/main/LICENSE).
* **DJL Hugging Face Tokenizers:** Used for handling text tokenization tasks. Licensed under the [Apache 2.0 License](https://github.com/deepjavalibrary/djl/blob/master/LICENSE).
* **OpenCV (via org.bytedeco):** If you use image processing functionalities within this project, it indirectly depends on OpenCV. Licensed under the [Apache 2.0 License](https://github.com/bytedeco/javacpp-presets/blob/master/LICENSE) (for JavaCPP Presets) and the [Apache 2.0 License](https://opencv.org/license/) (for OpenCV itself).
* **Apache Commons Math3:** Provides common mathematical and statistical utilities. Licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).

-----

## Contribution

Contributions and suggestions are welcome\! Feel free to submit Issues or Pull Requests.

-----

## Contact

[2563726816@qq.com](mailto:2563726816@qq.com)

-----
