# Etapa de build
FROM node:18 AS build
WORKDIR /app

# Solo copiar lo necesario para instalar dependencias
COPY package*.json ./
RUN npm install

# Luego copiar el resto del proyecto (sin node_modules)
COPY . .
RUN npm run build --configuration=production

# Etapa de producción
FROM nginx:alpine
COPY --from=build /app/dist/solicitud-app /usr/share/nginx/html
EXPOSE 80