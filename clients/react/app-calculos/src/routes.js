import React from 'react';

import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';

import Login from './pages/Login'
import Cadastro from './pages/Cadastro';
import Sistema from './pages/Sistema';

export default function RoutesApp(){
    return (
        <Router>
            <Routes>
                <Route path="/" exact element={<Login/>} > </Route>
                <Route path="/login" element={<Login/>} > </Route>
                <Route path="/sistema" exact element={<Sistema/>} > </Route>
                <Route path='/cadastro' element={<Cadastro/>} > </Route>
            </Routes>
        </Router>
    );  
}