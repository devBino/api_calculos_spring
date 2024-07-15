import React from 'react';

import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';

import Login from './pages/Login'
import Cadastro from './pages/Cadastro';
import Sistema from './pages/Sistema';
import Calculos from './pages/Calculos';
import CalculoDetalhe from './pages/CalculoDetalhe';
import Anexos from './pages/Anexos';
import CalculosAnexo from './pages/CalculosAnexo';
import NotFound from './pages/NotFound';

export default function RoutesApp(){
    return (
        <Router>
            <Routes>
                <Route path="/" exact element={<Login/>} > </Route>
                <Route path="/login" element={<Login/>} > </Route>
                <Route path="/sistema" exact element={<Sistema/>} > </Route>
                <Route path='/cadastro' element={<Cadastro/>} > </Route>
                <Route path='/calculos' element={<Calculos/>} > </Route>
                <Route path='/calculo-detalhe' element={<CalculoDetalhe/>} > </Route>
                <Route path="/anexos" element={<Anexos/>} > </Route>
                <Route path="/calculos-anexo" element={<CalculosAnexo/>} > </Route>

                <Route path="*" element={<Navigate to="/pagina-nao-encontrada" />} />
                <Route path="/pagina-nao-encontrada" element={<NotFound />} />
                
            </Routes>
        </Router>
    );  
}