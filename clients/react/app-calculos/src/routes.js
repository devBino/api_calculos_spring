import React from 'react';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Login from './pages/Login'
import Cadastro from './pages/Cadastro';
import Sistema from './pages/Sistema';
import Calculos from './pages/Calculos';
import CalculoDetalhe from './pages/CalculoDetalhe';

export default function RoutesApp(){
    return (
        <Router>
            <Routes>
                <Route path="/" exact element={<Login/>} > </Route>
                <Route path="/login" element={<Login/>} > </Route>
                <Route path="/sistema" exact element={<Sistema/>} > </Route>
                <Route path='/cadastro' element={<Cadastro/>} > </Route>
                <Route path='/calculos' element={<Calculos/>} > </Route>
                <Route path='/calculo-detalhe/:id' element={<CalculoDetalhe/>} > </Route>
            </Routes>
        </Router>
    );  
}