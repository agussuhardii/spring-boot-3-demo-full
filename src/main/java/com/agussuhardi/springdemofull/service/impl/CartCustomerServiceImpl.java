package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.config.security.facade.UserInfo;
import com.agussuhardi.springdemofull.dto.CartDTO;
import com.agussuhardi.springdemofull.dto.CreateDTO;
import com.agussuhardi.springdemofull.entity.Cart;
import com.agussuhardi.springdemofull.entity.CartItem;
import com.agussuhardi.springdemofull.repository.CartItemRepository;
import com.agussuhardi.springdemofull.repository.CartRepository;
import com.agussuhardi.springdemofull.service.CartCustomerService;
import com.agussuhardi.springdemofull.service.ProductService;
import com.agussuhardi.springdemofull.vo.CartItemVO;
import com.agussuhardi.springdemofull.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartCustomerServiceImpl implements CartCustomerService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Override
    public CreateDTO addItems(CartVO vo) {
        var useId = UserInfo.getPrincipal().getId();

        var cart = cartRepository.findByUserId(useId)
                .orElse(cartRepository.save(Cart.builder()
                        .userId(useId)
                        .build()));

        if (vo.items() == null) return new CreateDTO(cart.getId());
        for (var item : vo.items()) {
            save(item, cart);
        }
        return new CreateDTO(cart.getId());
    }

    private void save(CartItemVO itemVO, Cart cart) {
        var product = productService.requireOne(itemVO.productId());
        CartItem cartItem = cart.getItems().stream().filter(existItem -> existItem.getProductId().equals(itemVO.productId())).findAny()
                .orElse(CartItem.builder()
                        .cart(cart)
                        .productId(itemVO.productId())
                        .qty(0L)
                        .build());


        if (itemVO.qty() > product.getQty()) cartItem.setQty(product.getQty());
        else cartItem.setQty(cartItem.getQty() + itemVO.qty());
        var e = cartItemRepository.save(cartItem);
    }


    @Override
    public CartDTO getByPrincipal() {
        Cart original = cartRepository.findByUserId(UserInfo.getPrincipal().getId()).orElse(new Cart());
        return toDTO(original);
    }

    private CartDTO toDTO(Cart original) {
        CartDTO bean = new CartDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

}
