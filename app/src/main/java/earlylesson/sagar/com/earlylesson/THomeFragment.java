package earlylesson.sagar.com.earlylesson;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class THomeFragment extends Fragment {
    Button btn_alert_type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thome_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_alert_type = view.findViewById(R.id.btn_alert_type);
        btn_alert_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), btn_alert_type);

                popup.getMenuInflater().inflate(R.menu.type_popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_lecture:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeLecture()).commit();
                                break;
                            case R.id.menu_off_period:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeOffPeriod()).commit();
                                break;
                            case R.id.menu_notice:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeNotice()).commit();
                                break;
                            case R.id.menu_checking:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeChecking()).commit();
                                break;
                            case R.id.menu_submission:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeSubmission()).commit();
                                break;
                            case R.id.menu_other:
                                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_child, new PostTypeOther()).commit();
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}