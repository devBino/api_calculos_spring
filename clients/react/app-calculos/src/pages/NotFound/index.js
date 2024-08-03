import React from "react";

import DivContainer from '../../pages/DivContainer';

export default function NotFound(){

    function redirectSistema() {
        window.location.href = '/sistema';
    }

    return (
        <DivContainer title='Pagina Não Encontrada...' pageNotFound={true}>

            <div class="card">
                            
                <h3 class="card-header">Uops...</h3>

                <div class="card-body">
                    
                    <p>Parece que você tentou acessar um página que não existe...</p>

                </div>

                <div class="card-footer">
                    <div class="row">
                        <div class="col-md-11"></div>
                        <div class="col-md-1">
                            <button type="submit" class="btn btn-primary" onClick={redirectSistema}>Entendi</button>
                        </div>
                    </div>
                </div>

            </div>

        </DivContainer>
    );
};