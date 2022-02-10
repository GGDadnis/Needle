import axios from 'axios';

const apilog = axios.create({
    baseURL: "http://localhost:8081/"
})

export default apilog;