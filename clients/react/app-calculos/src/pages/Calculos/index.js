import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import DivContainer from '../DivContainer';
import api from '../../services/api';

export default function Calculos(){

    const [calculos, setCalculos] = useState([]);
    const [pagina, setPagina] = useState(1);
    const [sinal, setSinal] = useState(0);
    const [totalPaginas, setTotalPaginas] = useState(1);
    const [totalRegistros, setTotalRegistros] = useState(1);
    
    const navigate = useNavigate();

    useEffect(()=>{

        const token = localStorage.getItem('token');
        
        let logado = token !== undefined && token !== null;

        if( !logado ){
            return;
        }

        const getPage = async function(){

            let paramSinalCalculo = localStorage.getItem('sinal_calculo') !== undefined && localStorage.getItem('sinal_calculo') !== null 
                ? `&sinal=${localStorage.getItem('sinal_calculo')}` : '';

            await api
                .get(`calculos/listar?page=${pagina}&limit=10${paramSinalCalculo}`)
                .then(response => {

                    setTotalPaginas( response.data.totalPaginas );
                    setTotalRegistros( response.data.totalRegistros );
    
                    if( response.data.calculos !== undefined && response.data.calculos.length > 0 ){
                        setCalculos(response.data.calculos);
                    }
                    
                })
                .catch(err => {
                    if( err.response.status === 401 ){
                        window.location.href = '/';
                    }
                });
        }

        getPage();

    }, [pagina, sinal]);

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

    function calculosPorSinal(pSinal, idBtn){
        
        localStorage.setItem('sinal_calculo', pSinal);
        
        setSinal(pSinal);
        setPagina(1);

        document.getElementById('sinalAdd').classList.remove('button-sm-clicado')
        document.getElementById('sinalSub').classList.remove('button-sm-clicado')
        document.getElementById('sinalMul').classList.remove('button-sm-clicado')
        document.getElementById('sinalDiv').classList.remove('button-sm-clicado')

        document.getElementById(idBtn).classList.add('button-sm-clicado')

    }

    return (
        <DivContainer title='Listagem de Calculos'>
            
            <div class="row">
                <div class="col-lg-8 col-md-7 col-sm-6 col-12">
                    <h3>Página: {pagina} de {totalPaginas} - Registros: {totalRegistros}</h3>
                </div>
                
                <div class="col-lg-4 col-md-5 col-sm-6 col-12 d-flex justify-content-end mb-2">

                    <div class="btn-group justify-content-end" role="group">
                        <button type="button" class="button-sm sinal" id="sinalAdd" onClick={() => calculosPorSinal(1, 'sinalAdd')}>+</button>
                        <button type="button" class="button-sm sinal" id="sinalSub" onClick={() => calculosPorSinal(2, 'sinalSub')}>-</button>
                        <button type="button" class="button-sm sinal" id="sinalMul" onClick={() => calculosPorSinal(3, 'sinalMul')}>*</button>
                        <button type="button" class="button-sm sinal" id="sinalDiv" onClick={() => calculosPorSinal(4, 'sinalDiv')}>/</button>
                        <button type="button" class="button-sm" onClick={prevPage}>Prev</button>
                        <button type="button" class="button-sm" onClick={nextPage}>Next</button>
                    </div>

                </div>

            </div>
            
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