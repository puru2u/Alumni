package com.example.ashish.alumini.fragments.common_fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.MainScreenActivity;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.mikepenz.iconics.view.IconicsImageView;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentWebView extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /*
    * Butterknife
    * */

    @Bind(R.id.webView)
    WebView mWebView;

    @Bind(R.id.imageViewWebViewError)
    IconicsImageView mImageView;

    PostLoginActivity mActivity;
    MainScreenActivity mMainScreenActivity;

    Bus mBus = new Bus();


    String TAG = getClass().getSimpleName();


    public FragmentWebView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWebView newInstance(String param1, String param2) {
        FragmentWebView fragment = new FragmentWebView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);



        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        // show progress bar
        mBus.post(true);

        if (getActivity() instanceof PostLoginActivity){
            mActivity = (PostLoginActivity) getActivity();
        }
        else if (getActivity() instanceof MainScreenActivity){
            mMainScreenActivity = (MainScreenActivity) getActivity();
        }


        mWebView.getSettings().setJavaScriptEnabled(true);

        // 1st param is url
        mWebView.loadUrl(mParam1);

        mWebView.canGoBackOrForward(5);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                // hiding progress bar
                mBus.post(false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Log.d(TAG,"Error while loading page" + error.toString());

                // hiding progress bar
                Boolean aBoolean = false;

                mBus.post(aBoolean);
            }
        });

        // show progress bar
        mBus.post(true);


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
