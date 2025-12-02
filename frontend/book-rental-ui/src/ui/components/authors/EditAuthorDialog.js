import React, { useState, useEffect } from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    MenuItem,
    FormControl,
    InputLabel,
    Select
} from '@mui/material';
import axios from 'axios';

const EditAuthorDialog = ({ open, onClose, author, onEdit }) => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [countryId, setCountryId] = useState('');
    const [countries, setCountries] = useState([]); // Список на земји
    const [loading, setLoading] = useState(false); // Статус за вчитување

    useEffect(() => {
        if (open) {
            // Ако е отворен дијалогот, барај земји преку API
            setLoading(true);  // Почни со вчитување
            axios.get('http://localhost:9095/api/country')
                .then((response) => {
                    setCountries(response.data);
                })
                .catch((error) => console.error("Грешка при вчитување на земји:", error))
                .finally(() => setLoading(false));  // Кога ќе заврши, исклучи статус за вчитување
        }
    }, [open]);

    useEffect(() => {
        if (author) {
            // Постави вредности кога авторот е поставен
            setName(author.name || '');
            setSurname(author.surname || '');
            setCountryId(author.country?.id || '');
        }
    }, [author]);

    const handleSubmit = () => {
        if (name.trim() && surname.trim() && countryId) {
            onEdit(author.id, { name, surname, countryId });
            onClose();
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Author</DialogTitle>
            <DialogContent>
                <TextField
                    autoFocus
                    margin="dense"
                    label="Name"
                    fullWidth
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    fullWidth
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        value={countryId}
                        onChange={(e) => setCountryId(e.target.value)}
                        label="Country"
                        disabled={loading}  // Дизајнирано да се оневозможи додека се вчитуваат земјите
                    >
                        {countries.map((country) => (
                            <MenuItem key={country.id} value={country.id}>
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary" disabled={loading}>
                    {loading ? 'Saving...' : 'Save'}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAuthorDialog;
