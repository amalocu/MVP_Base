package co.com.etn.mvp_base.repositories;

/**
 * Created by alexander.vasquez on 26/09/2017.
 */




        import java.io.InterruptedIOException;

        import java.net.SocketTimeoutException;


        import co.com.etn.mvp_base.helper.Constants;
        import retrofit.RetrofitError;

        import retrofit.client.Response;
        import retrofit.mime.TypedByteArray;


/**

 * Created by leidyzulu on 26/09/17.

 */



public class MapperError {





    public static RepositoryError convertRetrofitErrorToRepositoryError(RetrofitError retrofitError) {

        RepositoryError repositoryError;



        repositoryError = valdiateTimeOutToGetRepositoryError(retrofitError);

        if (repositoryError != null) {

            return repositoryError;

        }



        repositoryError = validateTheBodyToGetRepositoryError(retrofitError);

        if (repositoryError != null) {

            return repositoryError;

        }



        return getDefaulError();

    }



    private static RepositoryError validateTheBodyToGetRepositoryError(RetrofitError retrofitError) {

        RepositoryError repositoryError = null;

        Response response = retrofitError.getResponse();

        if (response != null) {

            int errorId = response.getStatus();

            String mensaje = Constants.DEFAUL_ERROR;

            if (errorId == Constants.UNAUTHORIZED_ERROR_CODE || errorId == Constants.NOT_FOUND_ERROR_CODE) {

               /* try {

                    ErrorDTO errorDTO = (ErrorDTO) retrofitError.getBodyAs(ErrorDTO.class);

                    if (errorDTO != null) {

                        mensaje = errorDTO.getMessage();

                    }

                } catch (Exception exception) {

                    exception.printStackTrace();

                }*/

            }

            mensaje =  new String(((TypedByteArray)retrofitError.getResponse().getBody()).getBytes());

            repositoryError = new RepositoryError(mensaje);

            repositoryError.setIdError(errorId);

        }

        return repositoryError;

    }



    private static RepositoryError valdiateTimeOutToGetRepositoryError(RetrofitError retrofitError) {

        if (retrofitError.getCause() != null && retrofitError.getCause() instanceof SocketTimeoutException

                || retrofitError.getCause() instanceof InterruptedIOException) {

            RepositoryError repositoryError = new RepositoryError(Constants.REQUEST_TIMEOUT_ERROR_MESSAGE);

            repositoryError.setIdError(Constants.DEFAUL_ERROR_CODE);

            return repositoryError;

        }

        return null;

    }



    public static RepositoryError getDefaulError() {

        RepositoryError repositoryError = new RepositoryError(Constants.DEFAUL_ERROR);

        repositoryError.setIdError(Constants.DEFAUL_ERROR_CODE);

        return repositoryError;

    }

}
