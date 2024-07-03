
export default function apiError(err){

    if( err.message !== undefined && err.message === 'Network Error' ){
        alert('Erro durante a conexão com serviço de calculos...\nPor favor tente mais tarde...');
        return;
    }

    if( err.response.status === 401 ){
        window.location.href = '/';
        return;
    }

}