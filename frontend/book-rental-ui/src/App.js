import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./ui/components/layout/Layout";
import HomePage from "./ui/pages/HomePage/HomePage";
import BookPage from "./ui/pages/BookPage/BookPage";
import AuthorPage from "./ui/pages/AuthorPage/AuthorPage";
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage";
import React from 'react';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="books" element={<BookPage />} />
                    <Route path="authors" element={<AuthorPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                </Route>
            </Routes>
        </Router>
    );
}

export default App;
