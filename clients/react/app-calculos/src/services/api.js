import axios from 'axios';

const hostname = window.location.hostname;

const api = axios.create({
    baseURL: `http://${hostname}:3000`
});

api.interceptors.request.use((config) => {
    
        const token = localStorage.getItem('token');

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;

    },
    (error) => {
        return Promise.reject(error);
    });

export default api;