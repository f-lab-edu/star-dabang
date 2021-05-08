package dabang.star.cafe.application;

import dabang.star.cafe.application.command.MyMenuCreateCommand;
import dabang.star.cafe.application.command.MyMenuUpdateCommand;
import dabang.star.cafe.application.data.*;
import dabang.star.cafe.application.exception.NoAuthorizationException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.domain.mymenu.MyMenu;
import dabang.star.cafe.domain.mymenu.MyMenuRepository;
import dabang.star.cafe.domain.product.ProductRepository;
import dabang.star.cafe.utils.CacheKey;
import dabang.star.cafe.utils.CacheName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MyMenuRepository myMenuRepository;

    @Cacheable(value = CacheName.CATEGORY, key = CacheKey.DEFAULT)
    public List<TypeCategoryData> getAllCategories() {
        List<TypeCategoryData> typeCategories = new ArrayList<>();
        CategoryType[] types = CategoryType.values();

        for (CategoryType type : types) {
            List<CategoryData> categoryData = categoryRepository.findAllByType(type.getKey());
            typeCategories.add(new TypeCategoryData(type, categoryData));
        }

        return typeCategories;
    }

    @Cacheable(value = CacheName.PRODUCT, key = "#categoryId")
    public List<ProductData> getProductsByCategoryId(int categoryId) {
        return productRepository.findAllByCategoryIdAndActive(categoryId);
    }

    @Transactional
    public void createMyMenu(long memberId, MyMenuCreateCommand myMenuCreateCommand) {
        Long productId = myMenuCreateCommand.getProductId();
        ProductData productData = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("product id does not exist : " + productId)
        );

        validMyMenuOptions(productData.getOptions(), myMenuCreateCommand.getOptionInfo());

        MyMenu myMenu = myMenuCreateCommand.toMyMenu(memberId);
        myMenuRepository.save(myMenu);
    }

    @Transactional(readOnly = true)
    public List<MyMenuInfoData> getMyMenu(long memberId) {
        List<MyMenuInfoData> myMenuInfoData = new ArrayList<>();
        List<MyMenuData> myMenus = myMenuRepository.findAllByMemberId(memberId);

        if (!myMenus.isEmpty()) {
            List<Long> productIds = myMenus.stream()
                    .map(MyMenuData::getProductId)
                    .distinct()
                    .collect(Collectors.toList());

            Map<Long, ProductData> productDataMap = productRepository.findByIds(productIds)
                    .stream()
                    .collect(Collectors.toMap(ProductData::getId, productData -> productData));


            myMenus.stream().map(myMenu -> {
                ProductData product = productDataMap.get(myMenu.getProductId()).copy();
                product.calcPrice(myMenu.getOptionInfo());
                return new MyMenuInfoData(myMenu.getId(), myMenu.getName(), product);
            }).forEach(myMenuInfoData::add);
        }

        return myMenuInfoData;
    }

    public void updateMyMenu(MyMenuUpdateCommand myMenuUpdateCommand, long myMenuId, long memberId) {
        MyMenu findMyMenu = myMenuRepository.findById(myMenuId).orElseThrow(
                () -> new ResourceNotFoundException("my menu does not exist : " + myMenuId)
        );

        if (findMyMenu.getMemberId() != memberId) {
            throw new NoAuthorizationException("no authorization");
        }
        if (myMenuUpdateCommand.getOptionInfo() != null) {
            ProductData productData = productRepository.findById(findMyMenu.getProductId()).orElseThrow(
                    () -> new ResourceNotFoundException("product does not exist : " + findMyMenu.getProductId())
            );
            validMyMenuOptions(productData.getOptions(), myMenuUpdateCommand.getOptionInfo());
        }

        MyMenu myMenu = myMenuUpdateCommand.toMyMenu(findMyMenu.getId(), findMyMenu.getMemberId(), findMyMenu.getProductId());
        myMenuRepository.save(myMenu);
    }

    private void validMyMenuOptions(List<ProductOptionData> productOptions, Map<Long, Integer> myMenuOptionInfo) {
        if (myMenuOptionInfo.size() != productOptions.size()) {
            throw new ResourceNotFoundException("options not match");
        }

        for (ProductOptionData option : productOptions) {
            long optionId = option.getOptionId();
            if (!myMenuOptionInfo.containsKey(optionId)) {
                throw new ResourceNotFoundException("option id does not exist : " + optionId);
            }
            if (option.getMaxQuantity() < myMenuOptionInfo.get(optionId)) {
                throw new ValidationException("not valid quantity by option id : " + optionId + " max quantity : " + option.getMaxQuantity());
            }
        }
    }

    public void deleteMyMenu(long myMenuId, long memberId) {
        if (myMenuRepository.deleteByIdAndMemberId(myMenuId, memberId) == 0) {
            throw new ResourceNotFoundException("my menu id does not exists : " + myMenuId);
        }
    }

}