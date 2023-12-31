# BUILD REACT FRONTEND:
FROM node:18.14.0-alpine as frontend
WORKDIR /app/frontend
COPY package.json ./
COPY package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# BUILD EXPRESS FRONTEND SERVER:
FROM node:18.14.0-alpine as express-server
WORKDIR /app/express-server
COPY frontendexpressserver/package.json ./
COPY frontendexpressserver/package-lock.json ./
RUN npm install
COPY frontendexpressserver/server.js .
COPY --from=frontend /app/frontend/dist ./static
EXPOSE 3000
CMD ["node", "server.js"]


