package JavaAiLocalKit.demo;


import JavaAiLocalKit.api.TextGenerationApi;
import ai.onnxruntime.OrtException;


import java.io.IOException;

public class TextGenerationDemo {

    public static void main(String[] args) throws IOException, OrtException {
        // Path to the tokenizer configuration file.
        // Note: This example uses a classpath resource for the tokenizer JSON file.
        String tokenizerPath = "classpath:/TextGenerationDeepSeek/tokenizer.json";

        /*
         * Path to the ONNX model file.
         * IMPORTANT:
         * - Because ONNX models often have separate weight files, it is recommended to use
         *   a local absolute path to load the model directly.
         * - If you do not have the separate weight files, you may use a resource path instead.
         */
        String modelPath = "D:\\JAVA_code\\common-ai\\src\\main\\resources\\TextGenerationDeepSeek\\model.onnx";

        // Initialize the TextGenerationApi service with the tokenizer and model paths.
        TextGenerationApi service = new TextGenerationApi(tokenizerPath, modelPath);

        // Perform a chat interaction:
        // - "user123" is the user ID for session management.
        // - The prompt is "Please introduce the structure of the Earth" (in Chinese).
        // - 10 is the max number of new tokens generated per call.
        // - 300 is the max length of tokens kept for history (context window size).
        String reply = service.chat("user123", "Please tell us about the structure of the earth.", 10, 300);

        // Print the AI-generated reply to the console.
        System.out.println("AI reply: " + reply);

        // Optional: Clear chat history for this user.
        // service.clearChatHistory("user123", false);
    }
}

// 中文demo
//     public static void main(String[] args) throws IOException, OrtException {
//        // 分词器配置文件路径
//        // 说明：
//        // - 使用 classpath 资源路径加载 tokenizer.json 文件
//        // - 该文件应存放在项目的 resources/TextGenerationDeepSeek/ 目录下
//        String tokenizerPath = "classpath:/TextGenerationDeepSeek/tokenizer.json";
//
//        /*
//         * ONNX 模型文件路径
//         * 重要说明：
//         * - 由于 ONNX 模型通常包含多个权重文件，建议使用本地绝对路径直接加载模型
//         * - 如果没有分离的权重文件，可以使用资源路径替代
//         * - 示例中使用的是 Windows 系统绝对路径，Linux/Mac 需修改为相应格式
//         */
//        String modelPath = "D:\\JAVA_code\\common-ai\\src\\main\\resources\\TextGenerationDeepSeek\\model.onnx";
//
//        // 初始化文本生成服务
//        // 参数说明：
//        // - tokenizerPath: 分词器配置文件路径
//        // - modelPath: ONNX 模型文件路径
//        TextGenerationApi service = new TextGenerationApi(tokenizerPath, modelPath);
//
//        // 执行对话交互
//        // 参数说明：
//        // - "user123" : 用户ID，用于会话管理（多用户场景区分不同会话）
//        // - "Please tell us about the structure of the earth." : 用户输入的提示文本（英文）
//        // - 10 : 每次调用生成的最大新token数量（控制生成内容的长度）
//        // - 300 : 保留的历史token最大长度（控制上下文窗口大小）
//        String reply = service.chat("user123", "Please tell us about the structure of the earth.", 10, 300);
//
//        // 打印AI生成的回复内容
//        System.out.println("AI reply: " + reply);
//
//        /*
//         * 可选操作：清除该用户的聊天历史记录
//         * 参数说明：
//         * - "user123" : 要清除历史的用户ID
//         * - false : 是否完全重置用户会话（true=完全重置，false=仅清除历史）
//         */
//        // service.clearChatHistory("user123", false);
//    }

