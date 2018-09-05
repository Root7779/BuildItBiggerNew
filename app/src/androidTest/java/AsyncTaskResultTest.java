import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskResultTest extends AndroidTestCase implements EndpointsAsyncTask.AsyncCallbackBegin,EndpointsAsyncTask.AsyncCallback{
    private static final String TAG = "AsyncTaskResultTest";
    /**
     * Method to test that whatever is given to AsyncTask is returning through Google Cloud Endpoints
     * here is given Test and checking if Test is returned
     */
    @Test
    public void checkOutputAsyncTaskIsNullOrNot(){
        //Create AsyncTask
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(getContext(),this,this);
        try {
            String str = asyncTask.execute("Test").get();
            assertEquals("Test",str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getString(String jokeString) {
        Log.d(TAG, "getString: Sucessfully Called GetString Method");
    }

    @Override
    public void startedProgress(boolean isStarted) {
        Log.d(TAG, "startedProgress: Sucessfully Started AsyncTask And Data Retriving");
    }
}
