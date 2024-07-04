import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';
import apiError from '../../services/apiError';
import DivContainer from '../DivContainer';

export default function CalculosAnexo(){

    const navigate = useNavigate();
    
    const [pagina, setPagina] = useState(1);
    const [totalPaginas, setTotalPaginas] = useState(1);
    const [totalRegistros, setTotalRegistros] = useState(1);
    const [calculos, setCalculos] = useState([]);

    useEffect(()=>{
        
        const token = localStorage.getItem('token');
        
        let logado = token !== undefined && token !== null;

        if( !logado ){
            return;
        }

        const getPage = async function(){

            await api.get(`calculos/listar-por-anexo?page=${pagina}&limit=10&anexoId=${ localStorage.getItem('anexo_id') }`)
                .then(response => {
                    setTotalPaginas( response.data.totalPaginas );
                    setTotalRegistros( response.data.totalRegistros );
                
                    if( response.data.calculos !== undefined && response.data.calculos.length > 0 ){
                        setCalculos(response.data.calculos);
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

    function detalhesCalculo(pId){
        localStorage.setItem('calculo_id', pId);
        navigate(`/calculo-detalhe`);
    }

    return (

        <DivContainer title={`Calculos do Anexo ${localStorage.getItem('nome_anexo')}`}>

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-lg-8 col-md-8 col-sm-6 col-12">
                    <h3>Página: {pagina} de {totalPaginas} - Registros: {totalRegistros}</h3>
                </div>

                <div class="col-lg-2 col-md-2 col-sm-6 col-12 d-flex justify-content-end mb-2">

                    <div class="btn-group justify-content-end" role="group">
                        <button type="button" class="button-sm" onClick={prevPage}>Prev</button>
                        <button type="button" class="button-sm" onClick={nextPage}>Next</button>
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
                                    <th scope="col">UUID</th>
                                    <th scope="col">Anexo ID</th>
                                    <th scope="col">Valor 1</th>
                                    <th scope="col">Valor 2</th>
                                    <th scope="col">Sinal</th>
                                    <th scope="col">Resultado</th>
                                    <th scope="col">Descrição</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">-</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    calculos.map(c => (
                                        <tr>    
                                            <th scope="row"></th>
                                            <td>{c.id}</td>
                                            <td>{c.calculoUU}</td>
                                            <td>{c.anexoId}</td>
                                            <td>{c.numero1}</td>
                                            <td>{c.numero2}</td>
                                            <td>{c.sinal}</td>
                                            <td>{c.resultado.toFixed(4)}</td>
                                            <td>{c.descricao}</td>
                                            <td>{c.estado}</td>
                                            <td><button className="button" class="button-sm" onClick={() => detalhesCalculo(c.id)} >Ver</button></td>
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