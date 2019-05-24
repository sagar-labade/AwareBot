package earlylesson.sagar.com.earlylesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

public class BottomNavigationFragment extends Fragment {
    BottomNavigationView bottomNavigationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.bottom_navigation_fragment, container, false);


        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.bottom_navigation_container, new THomeFragment()).commit();

        }

        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.create_post:
                        getChildFragmentManager().beginTransaction().replace(R.id.bottom_navigation_container, new THomeFragment()).commit();
                        break;
                    case R.id.see_posts:

                        PopupMenu popup = new PopupMenu(getActivity(), view.findViewById(R.id.see_posts));
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.type_class, popup.getMenu());

                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Bundle bundle = new Bundle();
                                SHomeFragment obj = new SHomeFragment();
                                switch (item.getItemId()) {
                                    case R.id.menu_first_year:

                                        bundle.putString("data", "First Year");
                                        obj.setArguments(bundle);
                                        getChildFragmentManager().beginTransaction().replace(R.id.bottom_navigation_container, obj).commit();
                                        break;

                                    case R.id.menu_second_year:

                                        bundle.putString("data", "Second Year");
                                        obj.setArguments(bundle);
                                        getChildFragmentManager().beginTransaction().replace(R.id.bottom_navigation_container, obj).commit();
                                        break;

                                    case R.id.menu_third_year:

                                        bundle.putString("data", "Third Year");

                                        obj.setArguments(bundle);
                                        getChildFragmentManager().beginTransaction().replace(R.id.bottom_navigation_container, obj).commit();
                                        break;

                                }


                                return true;
                            }
                        });
                        popup.show();
                        break;
                }
                return true;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
