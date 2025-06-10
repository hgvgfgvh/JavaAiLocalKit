# 简易本地加载 AI 模型的 Java 依赖库

---

## 核心特点

* **极简 API：** 专注于 AI 任务本身，无需关心 ONNX Runtime 的复杂性，降低开发门槛。
* **本地推理：** 所有计算均在本地设备进行，无需联网，有效保护数据隐私并显著降低推理延迟。
* **高性能：** 理论上可以实现GPU 加速（因CUDA版本差异，目前暂未具体提供，未来将提供更便捷的统一解决方案）。
* **跨平台兼容：** 支持 Windows, Linux, macOS 等主流操作系统（通过 ONNX Runtime 实现）。
* **易于集成：** 可无缝集成到 Spring Boot 等各类 Java 项目中（未来将提供 Spring Boot Starter 以进一步简化集成）。
* **清晰的文档与示例：** 提供详尽的文档和开箱即用的示例代码，助您快速上手，即刻体验 AI 的强大。

---

## 已支持的任务类型 (及 Demo 代码)

* **文本分类 (Text Classification):** 适用于情感分析、垃圾邮件检测等二分类或多分类任务。
    * [TextClassificationDemo.java](link/to/TextClassificationDemo.java) (情感分类示例)
* **文本生成 (Text Generation):** 支持多轮对话、内容创作等。
    * [TextGenerationDemo.java](link/to/TextGenerationDemo.java) (DeepSeek 对话示例)

### **特别注意！**

本项目致力于提供**高封装性**的 AI 能力，Java 开发工程师**无需关注任何底层细节**，只需引入依赖即可在原有 Java 项目中进行本地 AI 推理。

您**唯一需要关注的**是所使用的 AI 模型**支持的任务类型**。这一设计旨在参考 Python 中 `Transforms` 的概念，便于 Java 开发者与 AI 开发人员协调，确保模型与 API 的兼容性。

正因如此，ONNX 模型的基本输入/输出形式有所限制，目前的 Demo 仅能支持符合**常规规范的 ONNX 模型**直接使用。未来将添加 **ONNX 模型输入/输出检测工具类**，进一步减少 Java 开发工程师需关注的细节。

#### **现支持任务类型的 ONNX 模型规范一览表**

##### **文本分类 (Text Classification) (二分类类型为例)**
* **输入：**
    * `input_ids` (必需)
    * `attention_mask` (必需)
    * `token_type_ids` (可选，可接受该字段不提供)
* **输出：**
    * `logits` (必需)

##### **文本生成 (Text Generation)**
* **输入：**
    * `input_ids` (必需)
    * `attention_mask` (必需)
    * `position_ids` (必需)
* **输出：**
    * `logits` (必需)

---

## 计划支持的任务类型 (后续更新)

**🚀 优先开发任务：**

* 图像转文本 (Image-to-Text)
* 零样本物体检测 (Zero-Shot Object Detection)
* 特征提取 (Feature Extraction)
* 问答 (Question Answering)

**✨ 长期计划任务：**

* 音频分类 (Audio Classification)
* 文本转音频 (Text-to-Audio)
* Token 分类 (Token Classification)
* 表格问答 (Table Question Answering)
* 视觉问答 (Visual Question Answering)
* 文档问答 (Document Question Answering)
* 掩码填充 (Fill-Mask)
* 文本摘要 (Summarization)
* 翻译 (Translation)
* 文本到文本生成 (Text2Text Generation)
* 零样本分类 (Zero-Shot Classification)
* 零样本图像分类 (Zero-Shot Image Classification)
* 零样本音频分类 (Zero-Shot Audio Classification)
* 图像特征提取 (Image Feature Extraction)
* 图像分割 (Image Segmentation)
* 图文转文本 (Image-Text-to-Text)
* 深度估计 (Depth Estimation)
* 掩码生成 (Mask Generation)
* 图像到图像 (Image-to-Image)

---

## 快速上手

1.  **获取核心 JAR 包:**
    * **如果您运行 Demo 项目:** 确保已将核心 JAR 包（如 `JavaAiLocalKit-Core-ai-1.0.0.jar`，可在项目 `target` 目录或 [Release 页面](link/to/release/page) 获取）**手动添加到您的 IDE (如 IntelliJ IDEA) 的 External Libraries**。
   



2. **添加额外依赖 (版本匹配):**

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

3. **运行 Demo:**

    下载并运行 `TextClassificationDemo.java` 或 `TextGenerationDemo.java`，即可体验文本分类和文本生成功能。

    #### **文本情绪分类**

    ```java
    public class TextClassificationDemo {

        public static void main(String[] args) throws Exception {
            // 指定 ONNX 模型和分词器文件路径（从资源目录加载）
            String tokenizerPath = "/TextClassificationDemoModelEnglish/tokenizer.json";
            String modelPath = "/TextClassificationDemoModelEnglish/model.onnx";

            // 待分析情感的文本
            String testText = "The room was terrible, not comfortable at all.";

            // 调用 TextClassificationApi 进行文本分类
            Map<String, Object> result = TextClassificationApi.classifyText(tokenizerPath, modelPath, testText);

            // 打印预测结果
            System.out.println("Prediction result: " + result.get("label"));

            // 提取并打印概率分布
            float[] probs = (float[]) result.get("probabilities");
            System.out.printf("Probability distribution: negative=%.4f, positive=%.4f%n", probs[0], probs[1]);
        }
    }
    ```

    #### **DeepSeek 对话**

    【可设置用户对话 ID、最大历史记录长度、最大生成 token 数，以及是否清除对话历史记忆】

    ```java
    public class TextGenerationDemo {

        public static void main(String[] args) throws IOException, OrtException {
            // 分词器配置文件的路径（本示例使用 classpath 资源）
            String tokenizerPath = "classpath:/TextGenerationDeepSeek/tokenizer.json";

            /*
             * ONNX 模型文件路径
             * 重要提示：
             * - 由于 ONNX 模型常带有独立权重文件，建议使用本地绝对路径直接加载模型。
             * - 如果模型不含独立权重文件，也可以使用 classpath 资源路径。
             */
            String modelPath = "D:\\JAVA_code\\common-ai\\src\\main\\resources\\TextGenerationDeepSeek\\model.onnx";

            // 初始化 TextGenerationApi 服务
            TextGenerationApi service = new TextGenerationApi(tokenizerPath, modelPath);

            // 执行一次对话交互：
            // - "user123" 为用于会话管理的唯一用户 ID。
            // - 提问内容为 "Please tell us about the structure of the earth."。
            // - 10 为每次调用最大生成的新 token 数量。
            // - 300 为历史记录（上下文窗口）保留的最大 token 长度。
            String reply = service.chat("user123", "Please tell us about the structure of the earth.", 10, 300);

            // 打印 AI 生成的回复
            System.out.println("AI reply: " + reply);

            // 可选：清除该用户的聊天历史记录
            // service.clearChatHistory("user123", false);
        }
    }
    ```

---

## 高级配置 (后续完善)

* **GPU 支持:** 目前默认使用 CPU。未来将提供更便捷的 CUDA 版本管理方案，支持 GPU 加速，让 Java 开发者仅关注于模型的使用。
* **多模型分布式部署：** 后续计划深度融入 Java 微服务体系，支持 AI 模型的分布式部署与管理。

---

## 商业合作与定制

如有特定业务需求场景，我们乐于提供从模型微调到 Java 部署的完整解决方案。欢迎联系详谈！

---

## 授权协议

本依赖库及提供的 Demo 案例遵循 **Apache 2.0 许可协议**。但**核心 JAR 包中的部分核心算法模块采用私有协议**，禁止反编译和未经授权的再发布。详细的协议条款请参见项目根目录下的 `LICENSE` 文件。

---

## 依赖声明与致谢

本项目在底层功能上依赖于以下出色开源库，并遵循其各自的许可协议。我们在此向它们表示衷心的感谢！

* **ONNX Runtime:** 用于高性能的机器学习模型推理。遵循 [MIT License](https://github.com/microsoft/onnxruntime/blob/main/LICENSE)。
* **DJL Hugging Face Tokenizers:** 用于处理文本分词任务。遵循 [Apache 2.0 License](https://github.com/deepjavalibrary/djl/blob/master/LICENSE)。
* **OpenCV (via org.bytedeco):** 如果您使用本项目中的图像处理相关功能，会间接依赖 OpenCV。遵循 [Apache 2.0 License](https://github.com/bytedeco/javacpp-presets/blob/master/LICENSE)（适用于 JavaCPP Presets）及 [Apache 2.0 License](https://opencv.org/license/)（适用于 OpenCV 本身）。
* **Apache Commons Math3:** 提供通用的数学和统计工具。遵循 [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0)。

---

## 贡献

欢迎通过提交 Issue 或 Pull Request 贡献代码和提出宝贵建议！

---

## 联系方式

[2563726816@qq.com](mailto:2563726816@qq.com)

---