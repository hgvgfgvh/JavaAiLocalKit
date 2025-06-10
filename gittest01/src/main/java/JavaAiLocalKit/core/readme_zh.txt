!!!!!需要将核心JAR包加入External Libraries!!!!

目前提供的简易demo任务类型,核心JAR可实现的任务类型：

text-generation     （demo代码：TextClassificationDemo.java）
text-generation     (demo代码：TextGenerationDemo,java)



后续更新的任务类型：

【image-to-text】
【zero-shot-object-detection】
【feature-extraction】
【question-answering】

audio-classification
text-to-audio
token-classification
table-question-answering
visual-question-answering
document-question-answering
fill-mask
summarization
translation
text2text-generation
zero-shot-classification
zero-shot-image-classification
zero-shot-audio-classification
image-feature-extraction
image-segmentation
image-text-to-text
depth-estimation
mask-generation
image-to-image


GPU实现可以进行，但是不同用户GPU CUDA 差异较大，后续将尝试指定便捷统一的解决方案，让JAVA开发人员仅关注于模型的调用


后续将融入 Spring Boot Starter 体系



注意！使用的额外依赖（尽量版本匹配）：
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