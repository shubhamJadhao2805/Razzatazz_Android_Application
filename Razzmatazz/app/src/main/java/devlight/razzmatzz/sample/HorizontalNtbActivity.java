package devlight.razzmatzz.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import devlight.razzmatzz.library.ntb.NavigationTabBar;

import java.util.ArrayList;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class HorizontalNtbActivity extends Activity {


    int[] logo = {R.drawable.mainevent,R.drawable.pc,R.drawable.singing,R.drawable.djnight,R.drawable.dance,R.drawable.fungame,R.drawable.fungame,R.drawable.nazzrana,R.drawable.drama,R.drawable.bharari,R.drawable.mushaira,R.drawable.rockband};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        initUI();
    }

    private void initUI() {
        final ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.layout.item_vp);
        arrayList.add(R.layout.item_vp2);
        arrayList.add(R.layout.item_vp3);
        arrayList.add(R.layout.item_vp4);
        arrayList.add(R.layout.item_vp5);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(getBaseContext()).inflate(arrayList.get(position), null, false);

//
//                final TextView txtPage = (TextView) view.findViewById(R.id.dayOneButton);
//
//                txtPage.setText(String.format("Page #%d", position));

                if(position == 3){

                    TextView dayOne = view.findViewById(R.id.dayOneButton);
                    dayOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(HorizontalNtbActivity.this,DayoneActivity.class);
                            startActivity(intent);
                        }
                    });

                    TextView dayTwo = view.findViewById(R.id.dayTwoButton);
                    dayTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(HorizontalNtbActivity.this,dayTwoActivity.class);
                            startActivity(intent);
                        }
                    });

                    TextView dayThree = view.findViewById(R.id.dayThreeButton);
                    dayThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(HorizontalNtbActivity.this,dayThree.class);
                            startActivity(intent);
                        }
                    });

                }


                if(position == 1){

                    GridView gridView = view.findViewById(R.id.eventView);
                    CustomAdapter customAdapter = new CustomAdapter(getBaseContext(),logo);
                    gridView.setAdapter(customAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            createImageView(position);
                        }
                    });




                }











                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.note_icon),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.note_icon))
                        .title("Register")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.event),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Event")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.home))
                        .title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.calendar),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Schedule")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_eighth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("About")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                Toast.makeText(HorizontalNtbActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//        }, 500);
//
    }

    public void createImageView(int imageID){
        ImageView image = new ImageView(this);


        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setClickable(true);
        image.setImageResource(logo[imageID]);
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setView(image);
        final AlertDialog show = builder.show();
        builder.setCancelable(true);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }
        });




    }
}