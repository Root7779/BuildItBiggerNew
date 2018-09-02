import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskResultTest extends AndroidTestCase{
    @Test
    public void checkOutputAsyncTaskIsNullOrNot(){
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(getContext());
        try {
            String str = asyncTask.execute("Test").get();
            assertEquals("Test",str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
