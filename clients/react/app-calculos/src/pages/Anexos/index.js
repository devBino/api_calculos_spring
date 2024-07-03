import React, {useEffect, useState} from "react";
import api from '../../services/api';
import apiError from '../../services/apiError';
import DivContainer from "../DivContainer";

export default function Anexos(){

    const [selectedFile, setSelectedFile] = useState(null);
    const [listaAnexos, setListaAnexos] = useState([]);
    const [pagina, setPagina] = useState(1);
    const [totalPaginas, setTotalPaginas] = useState(1);
    const [totalRegistros, setTotalRegistros] = useState(1);

    useEffect(()=>{

        const token = localStorage.getItem('token');
        
        let logado = token !== undefined && token !== null;

        if( !logado ){
            return;
        }

        const getPage = async function(){

            await api
                .get(`anexos/listar?page=${pagina}&limit=10`)
                .then(response => {

                    setTotalPaginas( response.data.totalPaginas );
                    setTotalRegistros( response.data.totalRegistros );
    
                    if( response.data.anexos !== undefined && response.data.anexos.length > 0 ){
                        setListaAnexos(response.data.anexos);
                    }


                })
                .catch(err => {
                    apiError(err);
                });
        }

        getPage();

    }, [pagina]);

    function nextPage(ev){
        ev.preventDefault();
        let pageAtual = ((pagina + 1) <= totalPaginas) ? pagina + 1 : 1;
        setPagina(pageAtual);
    }

    function prevPage(ev){
        ev.preventDefault();
        let pageAtual = ((pagina - 1) > 0) ? pagina - 1 : totalPaginas;
        setPagina(pageAtual)
    }

    const onChangeHandler = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const onClickHandler = () => {

        if( selectedFile === undefined || selectedFile === null ){
            alert('Por Favor, selecione um arquivo...');
            document.getElementById('arquivo').focus();
            return;
        }

        const data = new FormData();
        data.append('file', selectedFile);
    
        api.post('anexos/upload/csv', data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
        .then(response => {
            
            if( response.data.id !== undefined && response.data.id !== null ){
                alert('Arquivo Enviado com sucesso');
                return;
            }

            alert('Ocorreu um erro ao enviar o anexo...');

        })
        .catch(err => {
            apiError(err);
        });

    };

    function atualizar(){
        window.location.href = '/anexos';
    }

    function submitForm(ev){
        ev.preventDefault();
    }

    return (
        <DivContainer title="Upload de Anexos .csv">
            
            <div class="row mb-2">

                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <form class="form-control" onSubmit={submitForm}>

                        <div class="row p-2">
                            <div class="col-md-1 d-flex">
                                <label for="arquivo" class="form-label">Arquivo</label>
                            </div>
                            <div class="col-md-9">
                                <input type="file" id="arquivo" class="form-control" onChange={onChangeHandler}></input>
                            </div>
                            <div class="col-md-2">
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary" onClick={onClickHandler}>Enviar</button>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="col-md-1"></div>

            </div>

            <hr/>

            <div class="row mb-2">
                
                <div class="col-md-1"></div>
                <div class="col-lg-7 col-md-7 col-sm-6 col-12">
                    <h3>Página: {pagina} de {totalPaginas} - Registros: {totalRegistros}</h3>
                </div>
                
                <div class="col-lg-3 col-md-3 col-sm-6 col-12 d-flex justify-content-end mb-2">

                    <div class="btn-group justify-content-end" role="group">
                        <button type="button" class="button-sm" onClick={prevPage} >Prev</button>
                        <button type="button" class="button-sm" onClick={nextPage} >Next</button>
                        <button type="button" class="button-sm" onClick={atualizar} >Atualizar</button>
                    </div>

                </div>
                
                <div class="col-md-1"></div>

            </div>

            <hr/>

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">

                    <div class="table-responsive">

                        <table class="table" id="table" >
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Content-Type</th>
                                    <th scope="col">Data Criação</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">-</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    listaAnexos.map(a => (
                                        <tr>    
                                            <th scope="row"></th>
                                            <td>{a.id}</td>
                                            <td>{a.name}</td>
                                            <td>{a.contentType}</td>
                                            <td>{a.createdAt}</td>
                                            <td>{a.status}</td>
                                            <td><button className="button" class="button-sm">Ver</button></td>
                                        </tr>
                                     ))
                                }
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="col-md-1"></div>
            </div>

        </DivContainer>
    );

}