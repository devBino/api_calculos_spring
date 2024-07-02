import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import DivContainter from '../DivContainer';
import api from '../../services/api';

export default function CalculoDetalhe(){
    
    let {id} = useParams();

    const [dadosCalculo, setDadosCalculo] = useState({});
    const [historicos, setHistoricos] = useState([]);
    const [numero1, setNumero1] = useState('');
    const [numero2, setNumero2] = useState('');
    const [sinal, setSinal] = useState('');

    useEffect(()=>{

        const getCalculo = async function(){
            await api.get(`calculos/detalhar/${id}`)
                .then(response => {
                    setDadosCalculo(response.data);
                });
        }

        const historicos = async function(){
            await api.get(`calculo-historico/${id}`)
                .then(response => {
                    setHistoricos(response.data);
                });
        }

        getCalculo();
        historicos();

    }, [id])

    async function deletarCalculo(ev){
        
        ev.preventDefault();

        if( !window.confirm(`Deseja Realmente Deletar o Cálculo ${id}`) ){
            return;
        }
        
        try{
            await api.delete(`calculos/deletar/${id}`)
                .then(response => {
                    alert('Calculo deletado com sucesso');
                    window.location.href = '/calculos';
                });
        }catch(err){
            alert('Erro ao deletar o calculo');
        }

    }

    async function atualizarCalculo(ev){
            
        ev.preventDefault();

        try{

            await api.put('calculos/atualizar', {
                    id:id,
                    numero1:numero1,
                    numero2:numero2,
                    sinal:sinal
                })
                .then(response => {
                    
                    let status = response.status;
                    let id = response.data.id;
                    
                    if( status === 200 && id !== undefined ){
                        alert('Calculo Atualizado com Sucesso')
                        window.location.href = '/calculos';
                        return;
                    }

                    alert('Não foi possível concluir atualização do cálculo')

                })

        }catch(err){
            alert('Erro ao atualizar o calculo');
        }

    }

    return (
        <DivContainter title='Detalhes do Calculo'>
            
            <div class="row">

                <div class="col-md-3">

                    <form class="formulario">

                        <h3>Calculo Id: {dadosCalculo.id}</h3>

                        <div class="row p-2">

                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="numero1" class="form-label">Valor 1</label>
                            </div>
                            <div class="col-md-9">
                                <input type="number" id="numero1" name="numero1" step="0.01" class="form-control" aria-label="Primeiro Valor Numérico, inteiro ou decimal"
                                onChange={e => setNumero1(e.target.value)}></input>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="numero2" class="form-label">Valor 2</label>
                            </div>
                            <div class="col-md-9">
                                <input type="number" id="numero2" name="numero2" step="0.01" class="form-control" aria-label="Primeiro Valor Numérico, inteiro ou decimal"
                                onChange={e => setNumero2(e.target.value)}></input>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="sinal" class="form-label">Sinal</label>
                            </div>
                            <div class="col-md-9">
                                <select id="sinal" name="sinal" class="form-select" aria-label="Sinal para o cálculo matemático"
                                onChange={e => setSinal(e.target.value)}>
                                    <option></option>
                                    <option value="+">Adição</option>
                                    <option value="-">Subtração</option>
                                    <option value="*">Multiplicação</option>
                                    <option value="/">Divisão</option>
                                </select>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-6">
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary" onClick={deletarCalculo}>Deletar</button>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary" onClick={atualizarCalculo}>Salvar</button>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
                <div class="col-md-1"></div>
                <div class="col-md-8">
                    
                        <div class="card">
                            
                            <h3 class="card-header">Históricos do Cálculo</h3>

                            <div class="card-body">
                                
                                {                                    
                                    historicos.map(h => (
                                        <p class="card-text">{h.descricao}</p>
                                    ))
                                }

                            </div>

                        </div>
                </div>

            </div>

        </DivContainter>
    );
}