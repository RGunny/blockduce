FROM node:8.6 as build

WORKDIR /videochat
COPY package.json /videochat/
RUN npm install

COPY ./ /videochat

ARG VUE_APP_SOCKET_HOST=NOT_SET
ARG VUE_APP_SOCKET_PORT=NOT_SET

RUN export VUE_APP_SOCKET_HOST=${VUE_APP_SOCKET_HOST} VUE_APP_SOCKET_PORT=${VUE_APP_SOCKET_PORT} && npm run build

CMD ["npm", "run", "run:server"]

#######################################
FROM node:14.15.0 as builder
WORKDIR /app
COPY ./package.json ./
RUN npm install
COPY ./ ./
RUN npm build

FROM nginx
EXPOSE 3000
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/build /usr/share/nginx/html