package com.ebookfrenzy.primarydetailflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import com.ebookfrenzy.primarydetailflow.placeholder.PlaceholderContent
import com.ebookfrenzy.primarydetailflow.databinding.FragmentWebsiteDetailBinding

/**
 * A fragment representing a single Website detail screen.
 * This fragment is either contained in a [WebsiteListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class WebsiteDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var item: PlaceholderContent.PlaceholderItem? = null

    lateinit var itemDetailTextView: TextView

    private var _binding: FragmentWebsiteDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = PlaceholderContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWebsiteDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Show the placeholder content as text in a TextView.
        item?.let {
            binding.websiteDetail.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView, request: WebResourceRequest): Boolean {
                    return super.shouldOverrideUrlLoading(
                        view, request)
                }
            }
            binding.websiteDetail.loadUrl(it.website_url)
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}