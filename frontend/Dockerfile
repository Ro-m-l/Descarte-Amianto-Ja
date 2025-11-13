# Etapa 1: Build da aplicação React com Vite
FROM node:20-alpine AS frontend

WORKDIR /app

# Copia o package.json e o package-lock.json para instalar as dependências
COPY package.json package-lock.json ./

# Instala as dependências
RUN npm install

# Limpa o cache do npm para evitar pacotes corrompidos
#RUN npm cache clean --force

# Exclui os diretórios node_modules e dist para garantir que o cache do build seja limpo
#RUN rm -rf node_modules dist

# Copia o restante do código da aplicação para dentro do contêiner
COPY . .

# Instala as dependências novamente após copiar o código (caso o diretório node_modules tenha sido excluído anteriormente)
RUN npm install

# Cria o build da aplicação React
RUN npm run build

# Etapa 2: Servir a aplicação com Nginx
FROM nginx:alpine

# Garante que o diretório de destino existe
RUN mkdir -p /usr/share/nginx/html

# Copia o build do React para o diretório do Nginx
COPY --from=frontend /app/dist /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80
EXPOSE 443

# Configuração de Nginx já definida no seu default.conf
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]
