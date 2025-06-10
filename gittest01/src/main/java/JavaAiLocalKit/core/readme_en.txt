!!!!! The core JAR package needs to be added to External Libraries !!!!!

Currently available simple demo task types, which can be implemented by the core JAR:

    text-generation (Demo code: TextClassificationDemo.java)
    text-generation (Demo code: TextGenerationDemo.java)

Upcoming task types to be added:

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

GPU Implementation Notes:
GPU execution is possible, but due to significant variations in users' GPU/CUDA setups,
we will later explore a convenient and unified solution to allow Java developers to focus solely on model invocation.

Future Plans:

    Integration into the Spring Boot Starter ecosystem.

Important! Required dependencies (ensure version compatibility):


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