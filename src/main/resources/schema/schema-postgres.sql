CREATE TABLE SPRING_AI_CHAT_MEMORY (
                                       conversation_id text NOT NULL,
                                       content text NOT NULL,
                                       type text NOT NULL,
                                       "timestamp" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX SPRING_AI_CHAT_MEMORY_CONVERSATION_ID_TIMESTAMP_IDX ON SPRING_AI_CHAT_MEMORY(conversation_id, "timestamp" DESC);

ALTER TABLE SPRING_AI_CHAT_MEMORY ADD CONSTRAINT TYPE_CHECK CHECK (type IN ('USER', 'ASSISTANT', 'SYSTEM', 'TOOL'));