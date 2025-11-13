# Etapa 1: Build da aplicação React com Vite
FROM node:20-alpine AS frontend

WORKDIR /app

# Copia o package.json e o package-lock.json para instalar as dependências
COPY package.json package-lock.json ./

# Limpa o cache do npm para evitar pacotes corrompidos
RUN npm cache clean --force

# Exclui os diretórios node_modules e dist para garantir que o cache do build seja limpo
RUN rm -rf node_modules dist

# Instala as dependências novamente após copiar o código (caso o diretório node_modules tenha sido excluído anteriormente)
RUN npm install

# Copia o restante do código da aplicação para dentro do contêiner
COPY . .

# Cria o build da aplicação React
RUN npm run build

# Etapa 2: Servir a aplicação com Nginx
FROM nginx:alpine

# Copia o build do React para o diretório do Nginx
COPY --from=build /app/build /usr/share/nginx/html

# Corrige permissões no diretório do Nginx
RUN chmod -R 755 /usr/share/nginx/html
RUN chown -R nginx:nginx /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80

# Copia o arquivo de configuração do Nginx
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]
