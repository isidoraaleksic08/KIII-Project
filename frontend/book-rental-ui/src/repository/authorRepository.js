import axiosInstance from "../axios/axios";

const AUTHORS_API_URL = "/author";

const authorRepository = {
    findAll: () => axiosInstance.get(AUTHORS_API_URL),
    add: (author) => axiosInstance.post(`${AUTHORS_API_URL}/add`, author),
    edit: (id, author) => axiosInstance.put(`${AUTHORS_API_URL}/edit/${id}`, author),
    delete: (id) => axiosInstance.delete(`${AUTHORS_API_URL}/delete/${id}`)
};

export default authorRepository;
