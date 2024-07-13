
export default function apiError(err){

    //caso erros de conexão com API
    if( err.message !== undefined && err.message === 'Network Error' ){
        alert('Erro durante a conexão com serviço de calculos...\nPor favor tente mais tarde...');
        return;
    }

    if( err.response.status === 401 ){
        window.location.href = '/';
        return;
    }

    //caso algum erro padrão tenha sido retornado pela API
    if( err.response !== undefined && err.response.data !== undefined
        && err.response.data.erros !== undefined ){
           
        let erros = err.response.data.erros;

        let msgErro = 'A API Retornou o(s) seguinte(s) erro(s):\n\n';

        let keys = Object.keys( erros );

        keys.forEach((k) => {
            let e = erros[k];
            msgErro = `${msgErro}${e}\n`;
        });

        alert(msgErro);
        
    }

}