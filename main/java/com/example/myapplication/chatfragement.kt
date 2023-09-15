import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.adapters.CourseRVAdapter
import com.example.login.data.data_source.CourseRVModal
import com.example.myapplication.R

class chatfragement : Fragment() {

    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chatfragement, container, false)

        courseRV = view.findViewById(R.id.idRVCourses)
        courseList = ArrayList()
        courseRVAdapter = CourseRVAdapter(courseList)

        courseRV.adapter = courseRVAdapter

        courseList.add(CourseRVModal("Android Development", R.drawable.androidlogo))
        courseList.add(CourseRVModal("C++ Development", R.drawable.clogo))
        courseList.add(CourseRVModal("Java Development", R.drawable.javalogo))
        courseList.add(CourseRVModal("Python Development", R.drawable.pythonlogo))
        courseList.add(CourseRVModal("JavaScript Development", R.drawable.jslogo))

        courseRVAdapter.notifyDataSetChanged()

        // Inside your onCreateView method
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        // Set an OnQueryTextListener on the SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Call the filter method of your adapter to filter the data
                courseRVAdapter.filterList(courseList.filter {
                    it.courseName.contains(newText ?: "", ignoreCase = true)
                } as ArrayList<CourseRVModal>)
                return true
            }
        })

        return view
    }
}
