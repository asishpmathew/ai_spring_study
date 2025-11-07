# ai_spring_study

study github page link:  https://github.com/eazybytes/spring-ai
Spring ai doc: https://spring.io/projects/spring-ai

# OpenAi
- Open-ai billing information : https://platform.openai.com/settings/organization/billing/overview
- required 5 doller plan

#Prerequisites
- run podman-compose file to run the required dependencies such as ollama, qdrant, postgres
- ollam needs to be setup with following models
  - gemma3:4b : LLM model
  - mxbai-embed-large : Qdrant VectorStore requires this embedding model 
#install podman-compose
- install local python interpreter
- upgrade pip and podman-compose
  - pip install --upgrade pip 
  - pip install --upgrade podman-compose
- run the podman-compose-file : podman-compose -f podman-compose.yml up -d

#ollama models
- ollama run gemma3:4b

#Tools running
- Qdrant - http://localhost:6333/dashboard#/welcome
- Ollama webui :http://localhost:3000/