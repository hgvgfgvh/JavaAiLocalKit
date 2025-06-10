package JavaAiLocalKit.api;

import ai.onnxruntime.OrtException;
import git.JavaAiLocalKit.core.TextGenerationCore;

import java.io.IOException;

public class TextGenerationApi {
    // Core text generation engine instance that handles tokenization and model inference
    private final TextGenerationCore core;

    /**
     * Constructor to initialize the TextGenerationApi with specified tokenizer and model paths.
     *
     * @param tokenizerPath Path to the tokenizer configuration or vocabulary file.
     * @param modelPath     Path to the ONNX or other model file used for text generation.
     * @throws IOException   If the tokenizer or model files cannot be read.
     * @throws OrtException  If there is an error initializing the ONNX runtime environment.
     */
    public TextGenerationApi(String tokenizerPath, String modelPath) throws IOException, OrtException {
        this.core = new TextGenerationCore(tokenizerPath, modelPath);
    }

    /**
     * Generate AI text response for multi-turn conversations.
     *
     * This method supports conversational context by maintaining and
     * utilizing user-specific dialogue history.
     *
     * @param userId           Unique identifier for the user/session to maintain context.
     * @param userInput        Current input text from the user.
     * @param maxNewTokens     Maximum number of tokens to generate in the current response.
     * @param maxHistoryTokens Maximum number of historical tokens to retain in context to avoid exceeding model limits.
     * @return                 Generated AI text reply for the current turn.
     */
    public String chat(String userId, String userInput, int maxNewTokens, int maxHistoryTokens) {
        try {
            // Delegate generation to core engine, which handles tokenization, context, and inference
            return core.generate(userId, userInput, maxNewTokens, maxHistoryTokens);
        } catch (Exception e) {
            // On error, log the stack trace and return a user-friendly error message
            e.printStackTrace();
            return "⚠ AI reply failed: " + e.getMessage();
        }
    }

    /**
     * Clear the conversation history for a specific user.
     *
     * This can be used to reset context and start a fresh conversation session.
     *
     * @param userId         Unique identifier for the user/session whose history is to be cleared.
     * @param resetToInitial If true, preserves the initial prompt or system message after clearing history.
     */
    public void clearChatHistory(String userId, boolean resetToInitial) {
        core.clearUserHistory(userId, resetToInitial);
    }
}

