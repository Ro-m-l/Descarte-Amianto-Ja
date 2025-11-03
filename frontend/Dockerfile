# imagem do nginx
FROM nginx:stable-alpine

# copia os arquivos do projeto para o diretório de servir do nginx
COPY . /usr/share/nginx/html

# configuracao costumizada
COPY nginx.conf /etc/nginx/conf.d/default.conf

# expor porta 80 HTTP
EXPOSE 80