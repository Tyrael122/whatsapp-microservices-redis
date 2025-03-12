import http from 'k6/http';
import { check } from 'k6';
import { Trend } from 'k6/metrics';

const USER_SERVICE = 'http://localhost:8082';
const CHAT_SERVICE = 'http://localhost:8080';
const MESSAGE_SERVICE = 'http://localhost:8081';

let messageRetrievalTime = new Trend('message_retrieval_time', true);

export const options = {
    iterations: 10,
    vus: 10,
};

export default function () {
    let user1 = createUser('user1');
    let user2 = createUser('user2');

    let chatId = createChat([user1, user2]);

    for (let i = 0; i < 1000; i++) {
        sendMessage(chatId, user1, `Message ${i + 1} from user1`);
        sendMessage(chatId, user2, `Message ${i + 1} from user2`);
    }

    for (let i = 0; i < 10000; i++) {
        let startTime = new Date().getTime();  // Capture start time before getMessages
        getMessages(chatId);
        let endTime = new Date().getTime();    // Capture end time after getMessages
        let duration = endTime - startTime;   // Calculate the response time

        messageRetrievalTime.add(duration);
    }
}

function createUser(username) {
    let payload = JSON.stringify({ name: username });
    let params = { headers: { 'Content-Type': 'application/json' } };

    let res = http.post(`${USER_SERVICE}/users`, payload, params);
    check(res, { 'User created': (r) => r.status === 201 });

    return res.json().id; // Assuming response contains user "id"
}

function createChat(userIds) {
    let res = http.post(`${CHAT_SERVICE}/chats`, JSON.stringify({ name: 'Test Chat', users: userIds }), { headers: { 'Content-Type': 'application/json' } });
    check(res, { 'Chat created': (r) => r.status === 200 });

    return res.json().id;
}

function sendMessage(chatId, senderId, message) {
    let payload = JSON.stringify({ sender: senderId, content: message });
    let params = { headers: { 'Content-Type': 'application/json' } };

    let res = http.post(`${MESSAGE_SERVICE}/message-router/chat/${chatId}/send`, payload, params);
    check(res, { 'Message sent': (r) => r.status === 200 });
}

function getMessages(chatId) {
    let res = http.get(`${MESSAGE_SERVICE}/message-router/chat/${chatId}/messages`);
    check(res, { 'Messages retrieved': (r) => r.status === 200 });
}
