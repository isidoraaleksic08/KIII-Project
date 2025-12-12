import axiosInstance from "../axios/axios";

const BOOKS_API_URL = "/book";

const bookRepository = {
    findAll: () => axiosInstance.get(BOOKS_API_URL),
    add: (bookData) => axiosInstance.post(`${BOOKS_API_URL}/add`, bookData),
    edit: (id, bookData) => axiosInstance.put(`${BOOKS_API_URL}/edit/${id}`, bookData),
    delete: (id) => axiosInstance.delete(`${BOOKS_API_URL}/delete/${id}`)
};

export default bookRepository;
