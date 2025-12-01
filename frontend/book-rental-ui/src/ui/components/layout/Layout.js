import { Box, Container } from '@mui/material';
import Header from '../Header/Header';
import { Outlet } from 'react-router-dom';

const Layout = () => {
    return (
        <Box sx={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
            <Header />
            <Container sx={{ my: 2 }} maxWidth="xl">
                <Outlet />
            </Container>
        </Box>
    );
};

export default Layout;
