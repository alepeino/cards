(ns cards.views
  (:require
    [cards.components.card :refer [card]]
    [cards.components.new-card-form :refer [new-card-form]]
    [re-frame.core :as re-frame]))

(defn- all-cards [cards]
  [:div.card-columns
   (for [c cards] ^{:key (str (:type c) (:value c))}
     [card c])])

(defn main []
  (let [cards (re-frame/subscribe [:cards])]
    [:div.container
     (all-cards @cards)
     [:nav.navbar.bg-faded
      [:button.navbar-toggler.navbar-toggler-right
         {:type "button" :data-toggle "collapse" :data-target "#navbarSupportedContent" :aria-controls "navbarSupportedContent"
          :aria-expanded "false" :aria-label "Toggle navigation"}
         [:span.navbar-toggler-icon]]
      [:h2.text-dark.text-center "New"]
      [:div#navbarSupportedContent.collapse.navbar-collapse
         [:h2.text-dark.text-center "New"]
         [new-card-form #(re-frame/dispatch [:new-card %])]]]]))
