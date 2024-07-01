import React from "react";
import {Link} from 'react-router-dom';

export default function DivContainer({children, title, login}){
    
    let navBar = '';
    let conteudoRoot = '';
    
    let token = localStorage.getItem('token');
    let logado = token !== undefined && token !== null;
    
    //caso nao tenha logado não exibe menu superior
    if(logado){
            
        navBar = (    
            <div class="row">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <Link className="button" class="navbar-brand" to="/calculos">Calculos</Link>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <Link className="button" class="nav-link" to="/home">Home</Link>
                        </li>
                        <li class="nav-item">
                            <Link className="button" class="nav-link" to="/cadastro">Cadastro</Link>
                        </li>
                        <li class="nav-item">
                            <Link className="button" class="nav-link" to="/anexos">Anexos</Link>
                        </li>
                        <li class="nav-item">
                            <Link className="button" class="nav-link" to="/historicos">Historicos</Link>
                        </li>
                        <li class="nav-item">
                            <Link className="button" class="nav-link" to="/login">Logout</Link>
                        </li>
                    </ul>
                    </div>
                </div>
                </nav>
            </div>
        );

        conteudoRoot = children;

    }

    //caso não tenha logado e o componente atual seja a tela de login
    if( !logado && login !== undefined && login == '1' ){
        conteudoRoot = children;
    }

    return (
        <div class="container">
            
            {navBar}

            <div class="row mb-5">
                <div class="col-md-1 bg-primary">
                    <i class="bi bi-menu-down"></i>
                </div>
                <div class="col-md-11 bg-secondary">
                    <h3 class="text text-light">{title}</h3>
                </div>
            </div>

            <div class="row">
                {conteudoRoot}
            </div>
        </div>
    );
}