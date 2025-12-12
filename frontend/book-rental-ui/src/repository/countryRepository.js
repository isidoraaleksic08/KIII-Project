import axiosInstance from "../axios/axios";

const COUNTRIES_API_URL = "/country";

const countryRepository = {
    findAll: () => axiosInstance.get(COUNTRIES_API_URL),
    add: (countryData) => axiosInstance.post(`${COUNTRIES_API_URL}/add`, countryData),
    edit: (id, countryData) => axiosInstance.put(`${COUNTRIES_API_URL}/edit/${id}`, countryData),
    delete: (id) => axiosInstance.delete(`${COUNTRIES_API_URL}/delete/${id}`)
};

export default countryRepository;
