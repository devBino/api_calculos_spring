import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import DivContainer from "../DivContainer";
import api from '../../services/api';

export default function Login(){

    localStorage.clear();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    async function login(ev){
        
        ev.preventDefault();
        
        try{
            
            if( username === undefined || username === null || username === '' ){
                alert('Campo Usuário Obrigatório...');
                document.getElementById('usuario').focus();
                return;
            }

            if( password === undefined || password === null || password === '' ){
                alert('Campo Password Obrigatório...');
                document.getElementById('password').focus();
                return;
            }

            const response = await api.get(`auth/token/${username}/${password}`);

            localStorage.setItem('username', username);
            
            if( response.data.token === undefined || response.data.token === null ){
                alert('Login inválido, revise suas credenciais e tente novamente');
                document.getElementById('usuario').focus();
                return;
            }

            localStorage.setItem('token', response.data.token);
            
            navigate('/sistema');

        }catch(err){
            alert('Login falhou, tente novamente...');
        }

    };

    return (
        <DivContainer title="Login na Aplicação" login="1">
            
            <div class="row">

                <div class="col-md-4">

                </div>
                <div class="col-md-4">
                    <form class="form-control mt-4" onSubmit={login}>
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Usuário</label>
                            <input type="text" class="form-control" id="usuario"
                            value={username} onChange={e => setUsername(e.target.value)}></input>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password"
                            value={password} onChange={e => setPassword(e.target.value)}></input>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Acessar</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-4">

                </div>

            </div>
            
        </DivContainer>
    );
}